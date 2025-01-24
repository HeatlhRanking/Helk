package com.sejong.health.db.ranking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingRepository extends JpaRepository<RankingEntity,Long> {

    List<RankingEntity> findAllByOrderByRankingValueDesc();

}
