package com.xuxu.controller;

import com.xuxu.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xx
 */
@RestController
@RequestMapping("/memory")
@Slf4j
public class MemoryController {
    private Set<Map> cache = Collections.synchronizedSet(new HashSet<>());
    private Random random = new Random();

    @PostConstruct
    public void init() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            this.cache.clear();
        }, 10, 60, TimeUnit.SECONDS);
    }

    @PostMapping("/test/{num}")
    public void test(@PathVariable("num") Integer num) {
        log.info("this is memory test");
        num = num == null ? 10 : num;
        for (int c = 0; c < num; c++) {
            Map<String, User> userMap = new HashMap<>();
            for (int i = 0; i < 1000; i++) {
                String key = String.valueOf(System.currentTimeMillis());
                userMap.put(key, new User());

                if (random.nextInt() % 2 == 0) {
                    cache.add(userMap);
                }

                log.info("execute count:{}", (c +1) * (i + 1));
            }
        }
    }
}
