package com.xuxu.websocket;

import cn.hutool.core.lang.Pair;
import com.alibaba.fastjson.JSON;
import com.xuxu.handler.Handler;
import com.xuxu.handler.LoginHandler;
import com.xuxu.protocol.Request;
import com.xuxu.protocol.body.Body;
import com.xuxu.protocol.body.LoginBody;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xx
 */
@Component
@Slf4j
public class ProtocolDispatcher implements ApplicationContextAware {

    private ApplicationContext context;

    private static Map<String, Pair<Class, Class>> decoder = new HashMap<>();

    static {
        decoder.put("login", new Pair<>(LoginHandler.class, LoginBody.class));
    }

    public void dispatch(WebSocket webSocket, String s) {
        Request request = JSON.parseObject(s, Request.class);
        String protocolId = request.getProtocolId();
        Pair<Class, Class> decoderPair = this.decoder.get(protocolId);
        if (decoderPair == null) {
            log.error("not find decoder, key: {}", protocolId);
            webSocket.close();
            return;
        }

        Handler handler = (Handler) this.context.getBean(decoderPair.getKey());
        Body body = (Body) JSON.parseObject(request.getBody(), decoderPair.getValue());
        if (handler == null || body == null) {
            log.error("illegal client, close websocket");
            webSocket.close();
            return;
        }

        handler.handle(webSocket, protocolId, body);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
