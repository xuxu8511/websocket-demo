package com.xuxu.websocket;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * @author xx
 */
@Slf4j
public class MyWebClient extends WebSocketClient {
    public MyWebClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("{}", serverHandshake);

        send("{\"protocolId\":\"login\", \"body\": \"{\\\"userName\\\":\\\"xxxx\\\", \\\"password\\\":\\\"123456\\\"}\"}");
    }

    @Override
    public void onMessage(String s) {
        log.info("msg:{}", s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        log.info("closed");
    }

    @Override
    public void onError(Exception e) {
        log.info("e:{}", e.getMessage(), e);
    }
}
