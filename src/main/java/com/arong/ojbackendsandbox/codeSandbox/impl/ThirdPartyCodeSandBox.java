package com.arong.ojbackendsandbox.codeSandbox.impl;


import com.arong.ojbackendsandbox.codeSandbox.CodeSandbox;
import com.arong.ojbackendsandbox.codeSandbox.domain.JudgeInfoRequest;
import com.arong.ojbackendsandbox.codeSandbox.domain.JudgeInfoResponse;

public class ThirdPartyCodeSandBox implements CodeSandbox {
    @Override
    public JudgeInfoResponse execute(JudgeInfoRequest judgeInfoRequest) {
        System.out.println("第三方代码沙箱执行");
        return null;
    }
}
