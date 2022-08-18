package com.xuxu.handler;

import com.alibaba.fastjson.JSON;
import com.xuxu.protocol.Response;
import com.xuxu.protocol.body.LoginBody;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.springframework.stereotype.Component;

/**
 * @author xx
 */
@Component
@Slf4j
public class LoginHandler implements Handler<LoginBody> {

    @Override
    public void handle(WebSocket webSocket, String protocolId, LoginBody login) {
        log.info("login: {}", login);
        Response response = new Response();
        response.setProtocolId(protocolId);
        response.setRet("OK");
        response.setBody("abc");
        webSocket.send(JSON.toJSONString(response));
    }
}
