package com.kjstyle.jpaboard.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum SearchType {
    NAME("name"), USER_ID("user_id"), EMAIL("email");

    public static final Map<String, SearchType> map = new HashMap<>();

    static {
        for (SearchType st : SearchType.values()) {
            map.put(st.getValue(), st);
        }
    }

    private String value;

    public static SearchType findByValue(String s) {
        return map.get(s);
    }
}