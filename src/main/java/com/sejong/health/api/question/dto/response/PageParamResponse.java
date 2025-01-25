package com.sejong.health.api.question.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageParamResponse<T> {

    private Integer currentPage;
    private Integer size;
    private Integer totalElements;
    private Integer totalPages;
    private List<T> content;
}
