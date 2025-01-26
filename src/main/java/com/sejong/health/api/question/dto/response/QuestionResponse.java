package com.sejong.health.api.question.dto.response;

import com.sejong.health.db.answer.AnswerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResponse {

    private Long id;
    private String title;
    private String context;
    private Integer likes;
    private String madePerson;

    //추가
    private List<AnswerEntity> answers;
}
