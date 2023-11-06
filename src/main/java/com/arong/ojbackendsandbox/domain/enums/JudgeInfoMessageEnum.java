package com.arong.ojbackendsandbox.domain.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum JudgeInfoMessageEnum {

    ACCEPTED("成功", "Accepted"),

    WRONG_ANSWER("答案错误","Wrong Answer"),

    COMPILE_ERROR("编译错误", "Compile Error"),

    MEMORY_LIMIT_ERROR("内存溢出", "Memory Limit Error"),

    TIME_LIMIT_ERROR("超时", "Time Limit Error"),

    PRESENTATION_ERROR("展示错误", "Presentation Error"),

    WAITING("等待中", "Waiting"),

    OUTPUT_LIMIT_EXCEEDED("输出溢出", "Output Limit Exceeded"),

    DANGEROUS_OPERATION("危险操作", "Dangerous Operation"),

    RUNTIME_ERROR("运行错误", "Runtime Error"),

    SYSTEM_ERROR("系统错误", "System Error");





    private final String text;

    private final String value;

    JudgeInfoMessageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据枚举值获取信息
     * @param value
     * @return
     */
    public static JudgeInfoMessageEnum getEnumByValue(String value) {
        if(ObjectUtils.isEmpty(value)) {
            return null;
        }
        for(JudgeInfoMessageEnum judgeInfoMessageEnum : JudgeInfoMessageEnum.values()) {
            if(judgeInfoMessageEnum.value.equals(value)) {
                return judgeInfoMessageEnum;
            }
        }
        return null;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
