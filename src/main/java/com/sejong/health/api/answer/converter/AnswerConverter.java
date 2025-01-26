package com.sejong.health.api.answer.converter;

import com.sejong.health.api.answer.dto.request.AnswerFormRequest;
import com.sejong.health.api.answer.dto.response.AnswerFormResponse;
import com.sejong.health.common.annotation.Business;
import com.sejong.health.common.annotation.Converter;
import com.sejong.health.db.answer.AnswerEntity;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class AnswerConverter {

    public AnswerEntity toAnswerEntity(AnswerFormRequest answerFormRequest) {

        return AnswerEntity.builder()
                .likes(0)
                .context(answerFormRequest.getContext())
                .build();
    }

    public AnswerFormResponse toAnswerResponse(AnswerEntity answer) {

        return AnswerFormResponse.builder()
                .nickName(answer.getMember().getNickName())
                .context(answer.getContext())
                .likes(answer.getLikes())
                .build();
    }
}
