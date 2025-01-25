package com.sejong.health.common.test;

import com.sejong.health.api.member.service.MemberService;
import com.sejong.health.db.answer.AnswerEntity;
import com.sejong.health.db.answer.AnswerRepository;
import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.member.MemberRepository;
import com.sejong.health.db.member.enums.MemberStatus;
import com.sejong.health.db.question.QuestionEntity;
import com.sejong.health.db.question.QuestionRepository;
import com.sejong.health.db.ranking.RankingEntity;
import com.sejong.health.db.ranking.enums.RankingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SqlConstructor {

    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public void makeDbData(int count) {

        for (int i = 1; i <= count; i++) {
            MemberEntity member = getMemberEntity(i);

            RankingEntity ranking = getRankingEntity(i);
            member.makeRanking(ranking);

            memberRepository.save(member);
        }
    }

    public void makeMemberAndQuestion(int count) {
        List<MemberEntity> members = memberRepository.findAll();

        connectedMemberAndQuestion(count, members);

    }

    public void makeMemberAndQuestionAndAnswer(int count) {
        List<MemberEntity> members = memberRepository.findAll();
        List<QuestionEntity> questions = questionRepository.findAll();

        connectedMemberAndQuestionAndAnswer(count, questions, members);
    }

    private void connectedMemberAndQuestionAndAnswer(int count, List<QuestionEntity> questions, List<MemberEntity> members) {
        for (int i = 1; i <= count; i++) {
            AnswerEntity answer = AnswerEntity.builder()
                    .likes(i * 10)
                    .context("this is answer" + i)
                    .build();

            answer.makeQuestion(questions.get(i-1));
            answer.makeMember(members.get(i-1));
            answerRepository.save(answer);

        }
        for (int i = 1; i <= count; i++) {
            AnswerEntity answer = AnswerEntity.builder()
                    .likes(i * 10)
                    .context("this is not answer" + i)
                    .build();

            answer.makeQuestion(questions.get(i-1));
            answer.makeMember(members.get(i-1));
            answerRepository.save(answer);

        }
    }

    private void connectedMemberAndQuestion(int count, List<MemberEntity> members) {
        for (int i = 1; i <= count; i++) {
            QuestionEntity question = QuestionEntity.builder()
                    .likes(i * 10)
                    .context("this is question" + i)
                    .build();

            question.makeMember(members.get(i-1));
            questionRepository.save(question);
        }

        for (int i = 1; i <= count; i++) {
            QuestionEntity question = QuestionEntity.builder()
                    .likes(i * 10)
                    .context("this is not question" + i)
                    .build();

            question.makeMember(members.get(i-1));
            questionRepository.save(question);
        }
    }

    private RankingEntity getRankingEntity(int idx) {
        RankingEntity ranking = RankingEntity.builder()
                .rankingValue(100 * idx)
                .rankingStatus(RankingStatus.getTier(100 * idx))
                .build();
        return ranking;
    }

    private MemberEntity getMemberEntity(int idx) {
        MemberEntity member = MemberEntity.builder()
                .email("dongho" + idx + "@naver.com")
                .password("1234")
                .nickName("dongho" + idx)
                .status(MemberStatus.REGISTERED)
                .build();
        return member;
    }

}
