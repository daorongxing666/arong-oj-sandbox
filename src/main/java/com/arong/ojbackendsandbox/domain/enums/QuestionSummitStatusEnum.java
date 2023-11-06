package com.arong.ojbackendsandbox.domain.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum QuestionSummitStatusEnum {

    WAITING("等待中", 0),

    RUNNING("判题中", 1),

    SUCCEED("成功", 2),

    FAILED("失败", 3);



    private final String text;

    private final Integer value;

    QuestionSummitStatusEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据枚举值获取信息
     * @param value
     * @return
     */
    public static QuestionSummitStatusEnum getEnumByValue(Integer value) {
        if(ObjectUtils.isEmpty(value)) {
            return null;
        }
        for(QuestionSummitStatusEnum judgeInfoMessageEnum : QuestionSummitStatusEnum.values()) {
            if(judgeInfoMessageEnum.value.equals(value)) {
                return judgeInfoMessageEnum;
            }
        }
        return null;
    }

    public String getText() {
        return text;
    }

    public Integer getValue() {
        return value;
    }
}
