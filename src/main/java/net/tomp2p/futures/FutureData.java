/*
 * Copyright 2009 Thomas Bocek
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.tomp2p.futures;
import java.io.IOException;

import net.tomp2p.message.Message;
import net.tomp2p.utils.Utils;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * FutureData is used as the future object for direct DHT operations. Its adds
 * more logic that the generic {@link FutureResponse}, such as converting the
 * received buffer to an object.
 * 
 * @author Thomas Bocek
 * 
 */
public class FutureData extends FutureResponse
{
	// if set to raw, we expose the user directly to the Netty buffer, otherwise
	// we are converting byte[] arrays to objecs
	final private boolean raw;
	// we get either a buffer or an object
	private ChannelBuffer buffer;
	private Object object;

	/**
	 * Creates the request message for raw data. Note that the response might
	 * have a null payload. This is ok since a response might be empty and only
	 * send an ack that the message has arrived.
	 * 
	 * @param requestMessage The message that was sent to the remode peer
	 */
	public FutureData(final Message requestMessage, final boolean raw)
	{
		super(requestMessage);
		this.raw = raw;
	}

	@Override
	public void setResponse(final Message responseMessage)
	{
		synchronized (lock)
		{
			if (!setCompletedAndNotify())
			{
				return;
			}
			this.buffer = responseMessage.getPayload1();
			// even though the buffer is null, the type can be OK. In that case
			// an empty message was sent.
			this.type = responseMessage.getType() == Message.Type.OK ? FutureType.OK
					: FutureType.FAILED;
			this.reason = responseMessage.getType().toString();
			// check if the user is waiting for an object
			if (!raw && this.type == FutureType.OK && this.buffer != null)
			{
				try
				{
					this.object = Utils.decodeJavaObject(this.buffer.array(), this.buffer
							.arrayOffset(), this.buffer.capacity());
				}
				catch (ClassNotFoundException e)
				{
					this.reason = e.toString();
					this.type = FutureType.FAILED;
				}
				catch (IOException e)
				{
					this.reason = e.toString();
					this.type = FutureType.FAILED;
				}
			}
		}
		notifyListerenrs();
	}

	/**
	 * Returns the raw buffer or null if the answer was empty.
	 * 
	 * @return The transferred buffer
	 */
	public ChannelBuffer getBuffer()
	{
		synchronized (lock)
		{
			return buffer;
		}
	}

	/**
	 * Returns the object or null if the underlying buffer was raw or the answer
	 * was empty.
	 * 
	 * @return The transferred object
	 */
	public Object getObject()
	{
		synchronized (lock)
		{
			return object;
		}
	}
}
