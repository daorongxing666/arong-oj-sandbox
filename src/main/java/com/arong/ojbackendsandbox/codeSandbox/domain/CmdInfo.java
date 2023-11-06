package com.arong.ojbackendsandbox.codeSandbox.domain;

import lombok.Data;

@Data
public class CmdInfo {

    private Integer exitValue;

    private String succeedMsg;

    private String errorMsg;

    private Long time;
}
