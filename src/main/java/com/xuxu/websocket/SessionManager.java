package com.xuxu.websocket;

import com.xuxu.websocket.Attachment;
import org.java_websocket.WebSocket;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xx
 */
@Component
public class SessionManager {
    private Map<String, WebSocket> sessionMap =  new HashMap<>();
    private volatile Lock sessionLock = new ReentrantLock();

    public void addSession(WebSocket webSocket) {
        this.sessionLock.lock();
        try {
            String id = ((Attachment) webSocket.getAttachment()).getId();
            this.sessionMap.put(id, webSocket);
        } finally {
            this.sessionLock.unlock();
        }
    }

    public void delSession(WebSocket webSocket) {
        this.sessionLock.lock();
        try {
            String id = ((Attachment) webSocket.getAttachment()).getId();
            this.sessionMap.remove(id);
        } finally {
            this.sessionLock.unlock();
        }
    }
}
