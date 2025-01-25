package com.sejong.health.common.test;

import com.sejong.health.api.member.service.MemberService;
import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.member.MemberRepository;
import com.sejong.health.db.member.enums.MemberStatus;
import com.sejong.health.db.ranking.RankingEntity;
import com.sejong.health.db.ranking.enums.RankingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SqlConstructor {

    private final MemberRepository memberRepository;

    public  void makeDbData(int count){

        for (int i = 1; i <= count; i++) {
            MemberEntity member = getMemberEntity(i);

            RankingEntity ranking = getRankingEntity(i);
            member.makeRanking(ranking);

            memberRepository.save(member);
        }
    }

    private  RankingEntity getRankingEntity(int idx) {
        RankingEntity ranking = RankingEntity.builder()
                .rankingValue(100 * idx)
                .rankingStatus(RankingStatus.getTier(100 * idx))
                .build();
        return ranking;
    }

    private  MemberEntity getMemberEntity(int idx) {
        MemberEntity member = MemberEntity.builder()
                .email("dongho" + idx + "@naver.com")
                .password("1234")
                .nickName("dongho" + idx)
                .status(MemberStatus.REGISTERED)
                .build();
        return member;
    }

}
