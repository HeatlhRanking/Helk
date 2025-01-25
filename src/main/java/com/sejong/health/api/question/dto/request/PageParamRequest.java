package com.sejong.health.api.question.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParamRequest {

    Integer page =0;

    Integer size =5;
}
