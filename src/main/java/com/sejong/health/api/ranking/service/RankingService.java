package com.sejong.health.api.ranking.service;

import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.member.MemberRepository;
import com.sejong.health.db.ranking.RankingEntity;
import com.sejong.health.db.ranking.RankingRepository;
import com.sejong.health.db.ranking.enums.RankingStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;
    private final MemberRepository memberRepository;

    public void update(RankingEntity ranking) {
        Optional.ofNullable(ranking)
                .map(it -> {
                    return rankingRepository.save(ranking);

                }).orElseThrow(() -> new RuntimeException("ranking update"));
    }

    public List<RankingEntity> allRanking() {
        return rankingRepository.findAllByOrderByRankingValueDesc();
    }

    @Transactional
    public MemberEntity getScore(Long memberId) {
        return memberRepository.findById(memberId)
                .map(member -> {
                    RankingEntity ranking = member.getRanking();
                    if (ranking != null) {
                        int updateScore = ranking.getRankingValue() + 50;

                        ranking.setRankingValue(updateScore);
                        ranking.setRankingStatus(RankingStatus.getTier(updateScore));
                    }
                    return member; // 업데이트된 멤버 반환
                })
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId)); // 멤버가 없을 경우 예외 발생
    }

}
