package com.arong.ojbackendsandbox.codeSandbox.impl;

import java.util.ArrayList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.ObjectUtil;
import com.arong.ojbackendsandbox.codeSandbox.CodeSandbox;
import com.arong.ojbackendsandbox.codeSandbox.domain.CmdInfo;
import com.arong.ojbackendsandbox.codeSandbox.domain.JudgeInfo;
import com.arong.ojbackendsandbox.codeSandbox.domain.JudgeInfoRequest;
import com.arong.ojbackendsandbox.codeSandbox.domain.JudgeInfoResponse;
import com.arong.ojbackendsandbox.domain.enums.QuestionSummitStatusEnum;
import com.arong.ojbackendsandbox.utils.CmdUtils;

import javax.annotation.Resource;

public class JavaOriginSandbox implements CodeSandbox {

    /**
     * 代码存放文件夹位置
     */
    private static String CODE_DIR = "code";

    /**
     * 可执行java代码类名
     */
    private static String GLOBAL_CODE_NAME = "Main.java";


    public static void main(String[] args) {
        JavaOriginSandbox javaOriginSandbox = new JavaOriginSandbox();
        String code = ResourceUtil.readStr("runningCode" + File.separator + GLOBAL_CODE_NAME, StandardCharsets.UTF_8);
        JudgeInfoRequest judgeInfoRequest = new JudgeInfoRequest();
        judgeInfoRequest.setInputList(Arrays.asList("1 2", "3 4"));
        judgeInfoRequest.setCode(code);
        judgeInfoRequest.setLanguage("java");

        JudgeInfoResponse judgeInfoResponse = javaOriginSandbox.execute(judgeInfoRequest);
        System.out.println(judgeInfoResponse);
    }

    @Override
    public JudgeInfoResponse execute(JudgeInfoRequest judgeInfoRequest) {
        List<String> inputList = judgeInfoRequest.getInputList();
        String code = judgeInfoRequest.getCode();
        String language = judgeInfoRequest.getLanguage();

        // 代码存放路径
        String userDir = System.getProperty("user.dir");
        String path = userDir + File.separator + CODE_DIR;
        File file = new File(path);
        // 第一次启动不存在，创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
        // 隔离存放各种用户的输入
        String userPath = path + File.separator + UUID.randomUUID();
        // 将代码写入文件
        File writeString = FileUtil.writeString(code, userPath + File.separator + GLOBAL_CODE_NAME, StandardCharsets.UTF_8);

        // 调用CMD命令编译文件
        String compileCmd = String.format("javac -encoding UTF-8 %s", writeString.getAbsolutePath());
        CmdInfo compileCmdInfo = null;
        try {
            compileCmdInfo = CmdUtils.executeAndGetMsgByCmd(compileCmd, "编译");
        } catch (Exception e) {
            SandboxError(e);
        }
        System.out.println(compileCmdInfo);

        List<CmdInfo> cmdInfoList = new ArrayList<>();
        // 获取最大执行时间
        Long maxTime = 0L;
        for (String input : inputList) {
            // 防止栈内存溢出
            String execCmd = String.format("java -Xmx 256m -Dfile.encoding=UTF-8 -cp %s Main %s", userPath, input);
            CmdInfo execCmdInfo = null;
            try {
                execCmdInfo = CmdUtils.executeAndGetMsgByCmd(execCmd, "执行");
            } catch (Exception e) {
                SandboxError(e);
            }
            cmdInfoList.add(compileCmdInfo);
            maxTime = Math.max(maxTime, execCmdInfo.getTime());
            System.out.println(execCmdInfo);
        }

        // 封装响应结果
        JudgeInfoResponse judgeInfoResponse = new JudgeInfoResponse();
        List<String> outputList = new ArrayList<>();

        for (CmdInfo cmdInfo : cmdInfoList) {
            String errorMsg = cmdInfo.getErrorMsg();
            if(!ObjectUtil.isEmpty(errorMsg)) {
                // 有错误信息，直接返回
                judgeInfoResponse.setMsg(errorMsg);
                judgeInfoResponse.setStatus(QuestionSummitStatusEnum.FAILED.getText());
                return judgeInfoResponse;
            }
            outputList.add(cmdInfo.getSucceedMsg());
        }
        judgeInfoResponse.setOutputList(outputList);
        judgeInfoResponse.setStatus(QuestionSummitStatusEnum.SUCCEED.getText());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setTimeLimit(maxTime);
        judgeInfoResponse.setJudgeInfo(judgeInfo);

        // 删除文件
        if(writeString.getParentFile() != null) {
            boolean del = FileUtil.del(userPath);
            System.out.println("删除" + (del ? "成功" : "失败"));
        }
        return judgeInfoResponse;
    }

    /**
     * 异常返回类封装
     * @param e
     * @return
     */
    private JudgeInfoResponse SandboxError(Exception e) {
        JudgeInfoResponse judgeInfoResponse = new JudgeInfoResponse();
        judgeInfoResponse.setOutputList(new ArrayList<>());
        judgeInfoResponse.setMsg(e.getMessage());
        judgeInfoResponse.setStatus(QuestionSummitStatusEnum.FAILED.getText());
        return judgeInfoResponse;
    }
}
