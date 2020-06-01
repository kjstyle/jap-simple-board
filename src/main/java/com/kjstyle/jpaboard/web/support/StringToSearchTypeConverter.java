package com.kjstyle.jpaboard.web.support;

import com.kjstyle.jpaboard.enums.SearchType;
import org.springframework.core.convert.converter.Converter;

public class StringToSearchTypeConverter implements Converter<String, SearchType> {
    @Override
    public SearchType convert(String s) {
        return SearchType.valueOf(s);
    }
}
