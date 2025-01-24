package com.sejong.health.db.ranking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RankingStatus {

    DIAMOND("다이아"),
    PLATINUM("플레티넘"),
    GOLD("골드"),
    SILVER("실버"),
    BRONZE("브론즈"),
    ;

    private String status;

    public static RankingStatus getTier(int score) {
        if (score >= 2500) {
            return DIAMOND;
        } else if (score >= 2000) {
            return PLATINUM;
        } else if (score >= 1500) {
            return GOLD;
        } else if (score >= 1000) {
            return SILVER;
        } else {
            return BRONZE;
        }
    }
}
