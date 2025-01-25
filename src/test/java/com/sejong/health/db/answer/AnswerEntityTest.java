package com.sejong.health.db.answer;

import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.member.MemberRepository;
import com.sejong.health.db.question.QuestionEntity;
import com.sejong.health.db.question.QuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AnswerEntityTest {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void Init() {
        AnswerEntity answer = AnswerEntity.builder()
                .likes(20)
                .context("junsoo is king")
                .build();

        QuestionEntity questionEntity = questionRepository.findById(1L).get();
        MemberEntity memberEntity = memberRepository.findById(1L).get();

        answer.makeQuestion(questionEntity);
        answer.makeMember(memberEntity);

        answerRepository.save(answer);
    }

    @Test
    void AnswerEntity_Test() {
        AnswerEntity answer = answerRepository.findById(1L).get();

        MemberEntity member = answer.getMember();
        QuestionEntity question = answer.getQuestion();

        assertThat(member.getNickName()).isEqualTo("dongho1");
        assertThat(question.getContext()).isEqualTo("this is question1");
        assertThat(answer.getContext()).isEqualTo("this is answer1");
    }

}