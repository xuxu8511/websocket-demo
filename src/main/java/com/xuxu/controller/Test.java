package com.xuxu.controller;

import com.xuxu.websocket.MyWebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URI;

/**
 * @author xx
 */
@RestController
@RequestMapping("/test")
public class Test {

    @Value("${client.uri}")
    private URI clientUri;

    @RequestMapping(value = "/startClient/{num}", method = RequestMethod.POST)
    public void startClient(@PathVariable("num") Integer num) {
        while (true) {
            for (int i = 0; i < num; i++) {
                MyWebClient myWebClient = new MyWebClient(clientUri);
                myWebClient.connect();
                myWebClient.close();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
