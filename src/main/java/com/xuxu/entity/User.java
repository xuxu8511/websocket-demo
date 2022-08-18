package com.xuxu.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xx
 */
@Getter
@Setter
@ToString
public class User {
    private String id;
    private String name;
    private String desc = "11111111111111111111111111111111111111111111111111111111111111111111111111" + System.currentTimeMillis();
}
