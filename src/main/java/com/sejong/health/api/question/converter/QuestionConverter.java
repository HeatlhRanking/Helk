package com.sejong.health.api.question.converter;

import com.sejong.health.api.question.dto.request.QuestionFormRequest;
import com.sejong.health.api.question.dto.response.PageParamResponse;
import com.sejong.health.api.question.dto.response.QuestionResponse;
import com.sejong.health.common.annotation.Converter;
import com.sejong.health.db.question.QuestionEntity;
import org.hibernate.annotations.ConcreteProxy;
import org.springframework.data.domain.Page;

@Converter
public class QuestionConverter {

    public PageParamResponse<QuestionEntity> toPageParamResponse(Page<QuestionEntity> page){

       return PageParamResponse.<QuestionEntity>builder()
                .content(page.getContent())
                .currentPage(page.getNumber())
                .size(page.getSize())
                .totalElements((int) page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    public QuestionResponse toQuestionResponse(QuestionEntity questionEntity) {
        return QuestionResponse.builder()
                .id(questionEntity.getId())
                .title(questionEntity.getTitle())
                .context(questionEntity.getContext())
                .madePerson(questionEntity.getMember().getNickName())
                .likes(questionEntity.getLikes())
                .answers(questionEntity.getAnswers())
                .build();
    }

    public QuestionEntity toQuestionEntity(QuestionFormRequest request){
       return QuestionEntity.builder()
                .title(request.getTitle())
                .context(request.getContext())
                .likes(0)
                .build();
    }
}
