package com.arong.ojbackendsandbox.codeSandbox.domain;

import lombok.Data;

import java.util.List;

@Data
public class JudgeInfoResponse {

    /**
     * 输出用例
     */
    private List<String> outputList;

    /**
     * 执行信息
     */
    private String msg;

    /**
     * 状态
     */
    private String status;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;
}
