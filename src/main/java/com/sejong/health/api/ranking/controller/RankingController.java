package com.sejong.health.api.ranking.controller;

import com.sejong.health.api.ranking.business.RankingBusiness;
import com.sejong.health.common.Member;
import com.sejong.health.common.annotation.LoginMember;
import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.ranking.RankingEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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


    @GetMapping("/get/score")
    public String getScorePage(@LoginMember Member member, Model model) {

        model.addAttribute("member", member);
        return "/ranking/score";
    }

    @PostMapping("/get/score")
    public String updateScore(@LoginMember Member member,HttpSession session,Model model) {

        MemberEntity updateMember = rankingBusiness.getRankingScore(member.getId());
        session.removeAttribute("sessionId");
        session.setAttribute("sessionId", updateMember.getId());
        return "redirect:/api/ranking/get/score";
    }

}
