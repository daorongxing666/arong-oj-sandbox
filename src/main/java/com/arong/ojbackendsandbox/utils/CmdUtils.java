package com.arong.ojbackendsandbox.utils;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.StrUtil;
import com.arong.ojbackendsandbox.codeSandbox.domain.CmdInfo;
import com.arong.ojbackendsandbox.codeSandbox.domain.JudgeInfoResponse;
import com.arong.ojbackendsandbox.domain.enums.QuestionSummitStatusEnum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class CmdUtils {

    /**
     * cmd命令执行类
     * @param compileCmd
     * @param operation
     * @return
     */
    public static CmdInfo executeAndGetMsgByCmd(String compileCmd, String operation) throws Exception {
        CmdInfo cmdInfo = new CmdInfo();
        StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            Process process = Runtime.getRuntime().exec(compileCmd);
            // 防止超出时间限制
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    process.destroy();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
            int exitValue = process.waitFor();
            cmdInfo.setExitValue(exitValue);
            if (exitValue == 0) {
                // 正常退出
                System.out.println(operation + "成功");
                // 获取输出流
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder compileSuccessStringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    compileSuccessStringBuilder.append(line);
                }
                cmdInfo.setSucceedMsg(compileSuccessStringBuilder.toString());
            } else {
                // 不正常退出
                System.out.println(operation + "失败，exitValue = " + exitValue);
                // 不正常中正常的部分
                BufferedReader successBufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String successLine;
                StringBuilder compileSuccessStringBuilder = new StringBuilder();
                while ((successLine = successBufferedReader.readLine()) != null) {
                    compileSuccessStringBuilder.append(successLine);
                }
                cmdInfo.setSucceedMsg(compileSuccessStringBuilder.toString());
                // 不正常中不正常的部分
                BufferedReader ErrorBufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String ErrorLine;
                StringBuilder compileErrorStringBuilder = new StringBuilder();
                while ((ErrorLine = ErrorBufferedReader.readLine()) != null) {
                    compileErrorStringBuilder.append(ErrorLine);
                }
                cmdInfo.setErrorMsg(compileErrorStringBuilder.toString());
            }
            stopWatch.stop();
        long lastTaskTimeMillis = stopWatch.getLastTaskTimeMillis();
        cmdInfo.setTime(lastTaskTimeMillis);
        return cmdInfo;
    }

    /**
     * cmd交互类命令执行类
     * @param compileCmd
     * @param operation
     * @return
     */
    public static CmdInfo executeInteractAndGetMsgByCmd(String compileCmd, String operation, String inputArgs) {
        CmdInfo cmdInfo = new CmdInfo();
        try {
            // 模拟输入
            Process process = Runtime.getRuntime().exec(compileCmd);
            OutputStream outputStream = process.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            String[] s = inputArgs.split(" ");
            String join = StrUtil.join("\n", (Object) s) + "\n";
            // 输入一组用例
            outputStreamWriter.write(join);
            // 回车
            outputStreamWriter.flush();


            int exitValue = process.waitFor();
            cmdInfo.setExitValue(exitValue);
            if (exitValue == 0) {
                // 正常退出
                System.out.println(operation + "成功");
                // 获取输出流
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder compileSuccessStringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    compileSuccessStringBuilder.append(line);
                }
                cmdInfo.setSucceedMsg(compileSuccessStringBuilder.toString());
            } else {
                // 不正常退出
                System.out.println(operation + "失败，exitValue = " + exitValue);
                // 不正常中正常的部分
                BufferedReader successBufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String successLine;
                StringBuilder compileSuccessStringBuilder = new StringBuilder();
                while ((successLine = successBufferedReader.readLine()) != null) {
                    compileSuccessStringBuilder.append(successLine);
                }
                cmdInfo.setSucceedMsg(compileSuccessStringBuilder.toString());
                // 不正常中不正常的部分
                BufferedReader ErrorBufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String ErrorLine;
                StringBuilder compileErrorStringBuilder = new StringBuilder();
                while ((ErrorLine = ErrorBufferedReader.readLine()) != null) {
                    compileErrorStringBuilder.append(ErrorLine);
                }
                cmdInfo.setErrorMsg(compileErrorStringBuilder.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cmdInfo;
    }


}
