package com.sejong.health.api.answer.dto.response;

import com.sejong.health.db.member.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerFormResponse {

    private String context;

    private String nickName;

    private Integer likes;

}
