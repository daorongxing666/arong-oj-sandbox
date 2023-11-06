package com.arong.ojbackendsandbox.domain.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum QuestionSummitLanguageEnum {

    JAVA("java", "java"),

    CPP("cpp", "cpp"),

    GOLANG("go", "go");





    private final String text;

    private final String value;

    QuestionSummitLanguageEnum(String text, String value) {
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
    public static QuestionSummitLanguageEnum getEnumByValue(String value) {
        if(ObjectUtils.isEmpty(value)) {
            return null;
        }
        for(QuestionSummitLanguageEnum judgeInfoMessageEnum : QuestionSummitLanguageEnum.values()) {
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
