/*******************************************************************************
 * Copyright (c) 2021 Bosch.IO GmbH and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 * 
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v20.html
 * and the Eclipse Distribution License is available at
 *    http://www.eclipse.org/org/documents/edl-v10.html.
 * 
 * Contributors:
 *    Bosch IO.GmbH - initial creation
 ******************************************************************************/
package org.eclipse.californium.elements.config;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.eclipse.californium.elements.UDPConnector;
import org.eclipse.californium.elements.config.Configuration.DefinitionsProvider;
import org.eclipse.californium.elements.config.Configuration.IntegerDefinition;

/**
 * Configuration definitions for UDP.
 * 
 * @since 3.0
 */
public final class UdpConfig {

	public static final String MODULE = "UDP.";

	/**
	 * Number of receiver threads for {@link UDPConnector}.
	 */
	public static final IntegerDefinition UDP_RECEIVER_THREAD_COUNT = new IntegerDefinition(
			MODULE + "RECEIVER_THREAD_COUNT", "Number of UDP receiver threads.", 1);
	/**
	 * Number of sender threads for {@link UDPConnector}.
	 */
	public static final IntegerDefinition UDP_SENDER_THREAD_COUNT = new IntegerDefinition(
			MODULE + "SENDER_THREAD_COUNT", "Number of UDP sender threads.", 1);
	/**
	 * Size of {@link DatagramPacket} for {@link UDPConnector}.
	 */
	public static final IntegerDefinition UDP_DATAGRAM_SIZE = new IntegerDefinition(MODULE + "DATAGRAM_SIZE",
			"Maxium size of UDP datagram.", 2048);

	/**
	 * UDP receive buffer size used for
	 * {@link DatagramSocket#setReceiveBufferSize(int)}.
	 */
	public static final IntegerDefinition UDP_RECEIVE_BUFFER_SIZE = new IntegerDefinition(
			MODULE + "RECEIVE_BUFFER_SIZE", "UDP receive-buffer size.");
	/**
	 * UDP send buffer size used for
	 * {@link DatagramSocket#setSendBufferSize(int)}.
	 */
	public static final IntegerDefinition UDP_SEND_BUFFER_SIZE = new IntegerDefinition(MODULE + "SEND_BUFFER_SIZE",
			"UDP send-buffer size.");
	/**
	 * Maximum number of pending outbound messages.
	 */
	public static final IntegerDefinition UDP_CONNECTOR_OUT_CAPACITY = new IntegerDefinition(
			MODULE + "CONNECTOR_OUT_CAPACITY", "Maximum number of pending outgoing messages.", Integer.MAX_VALUE);

	static {
		Configuration.addModule(MODULE, new DefinitionsProvider() {

			@Override
			public void applyDefinitions(Configuration config) {
				final int CORES = Runtime.getRuntime().availableProcessors();
				final int THREADS = CORES > 3 ? 2 : 1;

				config.set(UDP_RECEIVER_THREAD_COUNT, THREADS);
				config.set(UDP_SENDER_THREAD_COUNT, THREADS);
				config.set(UDP_DATAGRAM_SIZE, 2048);
				config.set(UDP_RECEIVE_BUFFER_SIZE, null);
				config.set(UDP_SEND_BUFFER_SIZE, null);
				config.set(UDP_CONNECTOR_OUT_CAPACITY, Integer.MAX_VALUE);
			}
		});
	}

	public static void register() {
		SystemConfig.register();
	}
}
