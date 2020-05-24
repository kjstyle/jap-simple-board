package com.kjstyle.jpaboard.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * 공용 리스트 응답 DTO wrapper
 *
 * @param <T>
 */
@Getter
public class ListResponse<T> {
    private int current;
    private int rowCount;
    private List<T> rows;
    private long totalCount;

    @Builder
    public ListResponse(int current, int rowCount, List<T> rows, long totalCount) {
        this.current = current;
        this.rowCount = rowCount;
        this.rows = rows;
        this.totalCount = totalCount;
    }
}
