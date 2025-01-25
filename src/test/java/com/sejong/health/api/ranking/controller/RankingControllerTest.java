package com.sejong.health.api.ranking.controller;

import com.sejong.health.api.ranking.business.RankingBusiness;
import com.sejong.health.common.test.SqlConstructor;
import com.sejong.health.db.ranking.RankingEntity;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RankingControllerTest {

    @Autowired
    RankingBusiness rankingBusiness;;
    @Autowired
    SqlConstructor sqlConstructor;

    @BeforeEach
    void Init() {
        sqlConstructor.makeDbData(11);
    }

    @Test
    void 모든_랭킹_보여주기() {

        List<RankingEntity> rankingEntities = rankingBusiness.showAllRanking();
        Assertions.assertThat(rankingEntities.size()).isEqualTo(11);
        Assertions.assertThat(rankingEntities.get(0).getRankingValue()).isEqualTo(11*100);

    }
}