package com.sejong.health.api.question.service;

import com.sejong.health.db.answer.AnswerEntity;
import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.member.MemberRepository;
import com.sejong.health.db.question.QuestionEntity;
import com.sejong.health.db.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    public Page<QuestionEntity> getQuestionWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return questionRepository.findAll(pageable);
    }


    public QuestionEntity findById(Long questionId){
        return questionRepository.findById(questionId)
                .orElseThrow(()->new RuntimeException("question findById"));
    }

    @Transactional
    public Long update(Long memberId, QuestionEntity question){
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("question update"));

        question.makeMember(member);
        questionRepository.save(question);
        return question.getId();
    }
}
