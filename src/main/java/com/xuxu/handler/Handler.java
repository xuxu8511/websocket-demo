package com.xuxu.handler;

import org.java_websocket.WebSocket;

/**
 * @author xx
 */
public interface Handler<T> {
    void handle(WebSocket webSocket, String protocolId, T protocol);
}
