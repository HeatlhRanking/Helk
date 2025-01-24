package com.sejong.health.api.member.business;

import com.sejong.health.common.annotation.Business;
import com.sejong.health.api.member.converter.MemberConverter;
import com.sejong.health.api.member.dto.request.MemberLoginRequest;
import com.sejong.health.api.member.dto.request.MemberSignUpRequest;
import com.sejong.health.api.member.service.MemberService;
import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.ranking.RankingEntity;
import com.sejong.health.db.ranking.enums.RankingStatus;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Business
@RequiredArgsConstructor
public class MemberBusiness {

    private final MemberService memberService;
    private final MemberConverter memberConverter;

    public MemberEntity login(MemberLoginRequest request) {

        return memberService.login(request.getEmail(), request.getPassword());
    }

    public void signup(MemberSignUpRequest request) {

        MemberEntity memberEntity = memberConverter.toMemberEntity(request);
        RankingEntity rankingEntity = makeRankingEntity();

        memberEntity.makeRanking(rankingEntity);

        memberService.signup(memberEntity);

    }

    private RankingEntity makeRankingEntity() {
       return RankingEntity.builder()
                .createdDateTime(LocalDateTime.now())
                .modifiedDateTime(LocalDateTime.now())
                .rankingStatus(RankingStatus.BRONZE)
                .rankingValue(0)
                .build();


    }

}
