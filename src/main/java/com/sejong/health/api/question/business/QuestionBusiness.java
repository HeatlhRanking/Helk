package com.sejong.health.api.question.business;

import com.sejong.health.api.question.converter.QuestionConverter;
import com.sejong.health.api.question.dto.request.PageParamRequest;
import com.sejong.health.api.question.dto.response.PageParamResponse;
import com.sejong.health.api.question.dto.response.QuestionResponse;
import com.sejong.health.api.question.service.QuestionService;
import com.sejong.health.common.annotation.Business;
import com.sejong.health.db.question.QuestionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@Business
@RequiredArgsConstructor
public class QuestionBusiness {

    private final QuestionService questionService;
    private final QuestionConverter questionConverter;

    public PageParamResponse<QuestionEntity> getQuestionsPage(PageParamRequest pageParamRequest){
        Page<QuestionEntity> page = questionService.getQuestionWithPagination(pageParamRequest.getPage(), pageParamRequest.getSize());
        return questionConverter.toPageParamResponse(page);
    }

    public QuestionResponse getQuestionOne(Long questionId){

        QuestionEntity questionEntity = questionService.findById(questionId);
        return questionConverter.toQuestionResponse(questionEntity);
    }
}
