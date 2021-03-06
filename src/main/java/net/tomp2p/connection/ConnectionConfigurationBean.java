/*
 * Copyright 2011 Thomas Bocek
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
package net.tomp2p.connection;

/**
 * This bean sets the sharable configuration for the connection (TCP and UDP)
 * settings.
 * 
 * @author Thomas Bocek
 * 
 */
public class ConnectionConfigurationBean
{
	private int defaultPort = 7700;
	// idle needs to be larger than timeout for TCP
	private int idleTCPMillis = 6 * 1000;
	// private int timeoutTCPMillis = 3 * 1000;
	private int idleUDPMillis = 3 * 1000;
	private int connectTimeouMillis = 3 * 1000;
	// doing tests on localhost, we open 2 * maxOpenConnection
	private int maxOpenConnection = 400;
	private int maxCreating = 100;
	// max, message size to transmit
	private int maxMessageSize = 2 * 1024 * 1024;
	// force TCP or UDP
	private boolean forceTrackerTCP = false;
	private boolean forceStorageUDP = false;
	public int getIdleTCPMillis()
	{
		return idleTCPMillis;
	}

	public void setIdleTCPMillis(int idleTCPMillis)
	{
		this.idleTCPMillis = idleTCPMillis;
	}

	public int getIdleUDPMillis()
	{
		return idleUDPMillis;
	}

	public void setIdleUDPMillis(int idleUDPMillis)
	{
		this.idleUDPMillis = idleUDPMillis;
	}

	public int getConnectTimeoutMillis()
	{
		return connectTimeouMillis;
	}

	public void setConnectTimeoutMillis(int connectTimeouMillist)
	{
		this.connectTimeouMillis = connectTimeouMillist;
	}

	public void setDefaultPort(int defaultPort)
	{
		this.defaultPort = defaultPort;
	}

	public int getDefaultPort()
	{
		return defaultPort;
	}

	public void setMaxOpenConnection(int maxOpenConnection)
	{
		this.maxOpenConnection = maxOpenConnection;
	}

	public int getMaxOpenConnection()
	{
		return maxOpenConnection;
	}

	public void setMaxMessageSize(int maxMessageSize)
	{
		this.maxMessageSize = maxMessageSize;
	}

	public int getMaxMessageSize()
	{
		return maxMessageSize;
	}

	public int getMaxCreating()
	{
		return maxCreating;
	}

	public void setMaxCreating(int maxCreating)
	{
		this.maxCreating = maxCreating;
	}

	public boolean isForceTrackerTCP()
	{
		return forceTrackerTCP;
	}

	public void setForceTrackerTCP(boolean forceTrackerTCP)
	{
		this.forceTrackerTCP = forceTrackerTCP;
	}

	public boolean isForceStorageUDP()
	{
		return forceStorageUDP;
	}

	public void setForceStorageUDP(boolean forceStorageUDP)
	{
		this.forceStorageUDP = forceStorageUDP;
	}
}