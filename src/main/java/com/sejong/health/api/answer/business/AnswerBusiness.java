package com.sejong.health.api.answer.business;

import com.sejong.health.api.answer.controller.AnswerController;
import com.sejong.health.api.answer.converter.AnswerConverter;
import com.sejong.health.api.answer.dto.request.AnswerFormRequest;
import com.sejong.health.api.answer.dto.response.AnswerFormResponse;
import com.sejong.health.api.answer.service.AnswerService;
import com.sejong.health.common.annotation.Business;
import com.sejong.health.db.answer.AnswerEntity;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class AnswerBusiness {

    private final AnswerService answerService;
    private final AnswerConverter answerConverter;

    public AnswerFormResponse makeAnswer(AnswerFormRequest answerFormRequest, Long questionId, Long memberId) {

        AnswerEntity answerEntity = answerConverter.toAnswerEntity(answerFormRequest);

        AnswerEntity answer = answerService.save(answerEntity, questionId, memberId);

        return answerConverter.toAnswerResponse(answer);

    }
}
