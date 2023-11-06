package com.arong.ojbackendsandbox.codeSandbox.domain;

import lombok.Data;

/**
 * 判题信息
 */
@Data
public class JudgeInfo {

    /**
     * 时间限制
     */
    private Long timeLimit;

    /**
     * 内存限制
     */
    private Long memoryLimit;

    /**
     * 堆栈限制
     */
    private Long stackLimit;
}