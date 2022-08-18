package com.xuxu.protocol;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xx
 */
@Setter
@Getter
@ToString
public class Response {
    private String protocolId;
    private String ret;
    private String body;
}
