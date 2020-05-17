package com.kjstyle.jpaboard.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaginationParam {
    private int current;
    private int rowCount;
    private String sort;
}