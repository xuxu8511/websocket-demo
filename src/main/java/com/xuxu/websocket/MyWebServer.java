package com.xuxu.websocket;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @author xx
 */
@Component
@Slf4j
public class MyWebServer extends WebSocketServer implements InitializingBean {
    private Integer port;

    @Resource
    private SessionManager sessionManager;

    @Resource
    private ProtocolDispatcher protocolDispatcher;


    public MyWebServer(@Value("${server.web-port}") Integer port) {
        super(new InetSocketAddress(port));
        this.port = port;
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        log.info("onOpen:{}", webSocket);
        Attachment attachment = new Attachment();
        attachment.setId(String.valueOf(DateUtil.current()));
        webSocket.setAttachment(attachment);
        sessionManager.addSession(webSocket);
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        log.info("onClose:{}", webSocket);
        sessionManager.delSession(webSocket);
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        log.info("onMessage, websocket:{}, msg:{}", webSocket, s);
        protocolDispatcher.dispatch(webSocket, s);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
    }

    @Override
    public void onStart() {
        log.info("websocket start on port(s): {}", this.port);
    }

    @Override
    public void afterPropertiesSet() {
        this.start();
    }

}
