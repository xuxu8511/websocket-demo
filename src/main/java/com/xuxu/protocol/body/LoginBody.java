package com.xuxu.protocol.body;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xx
 */
@Setter
@Getter
@ToString
public class LoginBody implements Body {
    private String userName;
    private String password;
}