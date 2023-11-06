package com.arong.ojbackendsandbox.codeSandbox;

import com.arong.ojbackendsandbox.codeSandbox.domain.JudgeInfoRequest;
import com.arong.ojbackendsandbox.codeSandbox.domain.JudgeInfoResponse;

public interface CodeSandbox {

    public JudgeInfoResponse execute(JudgeInfoRequest judgeInfoRequest);
}
