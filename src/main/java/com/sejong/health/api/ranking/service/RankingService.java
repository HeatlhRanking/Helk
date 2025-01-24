package com.sejong.health.api.ranking.service;

import com.sejong.health.db.ranking.RankingEntity;
import com.sejong.health.db.ranking.RankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;

    public void update(RankingEntity ranking) {
        Optional.ofNullable(ranking)
                .map(it->{
                    return rankingRepository.save(ranking);

                }).orElseThrow(()->new RuntimeException("ranking update"));
    }

    public List<RankingEntity> allRanking(){
        return rankingRepository.findAllByOrderByRankingValueDesc();
    }

}
