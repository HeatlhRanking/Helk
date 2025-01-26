package com.sejong.health.api.answer.service;

import com.sejong.health.common.annotation.Business;
import com.sejong.health.db.answer.AnswerEntity;
import com.sejong.health.db.answer.AnswerRepository;
import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.member.MemberRepository;
import com.sejong.health.db.question.QuestionEntity;
import com.sejong.health.db.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public AnswerEntity save(AnswerEntity answerEntity,Long questionId, Long memberId) {

        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("answerEntity error"));
        QuestionEntity question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("answerEntity error"));

        return Optional.ofNullable(answerEntity)
                .map(it -> {
                    it.makeMember(member);
                    it.makeQuestion(question);
                    answerRepository.save(it);
                    return it;
                })
                .orElseThrow(() -> new RuntimeException("answerEntity error"));
    }
}
