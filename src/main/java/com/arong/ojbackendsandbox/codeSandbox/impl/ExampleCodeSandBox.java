package com.arong.ojbackendsandbox.codeSandbox.impl;


import com.arong.ojbackendsandbox.codeSandbox.CodeSandbox;
import com.arong.ojbackendsandbox.codeSandbox.domain.JudgeInfoRequest;
import com.arong.ojbackendsandbox.codeSandbox.domain.JudgeInfoResponse;

public class ExampleCodeSandBox implements CodeSandbox {
    @Override
    public JudgeInfoResponse execute(JudgeInfoRequest judgeInfoRequest) {
        System.out.println("示例代码沙箱执行");
        return null;
    }
}
