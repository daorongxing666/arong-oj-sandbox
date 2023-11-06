package com.arong.ojbackendsandbox.codeSandbox.impl;


import com.arong.ojbackendsandbox.codeSandbox.CodeSandbox;
import com.arong.ojbackendsandbox.codeSandbox.domain.JudgeInfoRequest;
import com.arong.ojbackendsandbox.codeSandbox.domain.JudgeInfoResponse;

public class RemoteCodeSandBox implements CodeSandbox {
    @Override
    public JudgeInfoResponse execute(JudgeInfoRequest judgeInfoRequest) {
        System.out.println("远程代码沙箱执行");
        return null;
    }
}
