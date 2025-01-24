package com.sejong.health.api.ranking.controller;

import com.sejong.health.api.ranking.business.RankingBusiness;
import com.sejong.health.db.ranking.RankingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/ranking")
public class RankingController {

    private final RankingBusiness rankingBusiness;

    @GetMapping("/all")
    public String allRanking(Model model){
        List<RankingEntity> rankings = rankingBusiness.showAllRanking();
        model.addAttribute("rankings",rankings);
        return "/ranking/rankings";
    }
}
