package com.sejong.health.api.ranking.business;

import com.sejong.health.api.ranking.service.RankingService;
import com.sejong.health.common.annotation.Business;
import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.ranking.RankingEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Business
@RequiredArgsConstructor
public class RankingBusiness {

    private final RankingService rankingService;


    public List<RankingEntity> showAllRanking(){
        return rankingService.allRanking();
    }

    public MemberEntity getRankingScore(MemberEntity memberEntity){
        return rankingService.getScore(memberEntity.getId());

    }
}
