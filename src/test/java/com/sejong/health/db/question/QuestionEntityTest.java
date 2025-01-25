package com.sejong.health.db.question;

import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class QuestionEntityTest {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void Init() {
        QuestionEntity question = QuestionEntity.builder()
                .likes(100)
                .context("Dongho is king")
                .build();

        MemberEntity member = memberRepository.findById(1L).get();
        question.makeMember(member);

        questionRepository.save(question);
    }

    @Test
    void QuestionEntity_테스트() {

        MemberEntity member = memberRepository.findById(1L).get();
        List<QuestionEntity> questions = member.getQuestions();

        Assertions.assertThat(questions.get(0).getContext()).isEqualTo("Dongho is king");
    }

}