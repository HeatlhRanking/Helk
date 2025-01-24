package com.sejong.health.api.member.controller;

import com.sejong.health.api.member.business.MemberBusiness;
import com.sejong.health.api.member.dto.request.MemberLoginRequest;
import com.sejong.health.db.member.MemberEntity;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/open-api")
public class MemberOpenApiController {

    private final MemberBusiness memberBusiness;


    @GetMapping("/login")
    public String login(){
        return "member/login";
    }

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute MemberLoginRequest request,
            Model model,
            BindingResult bindingResult,
            HttpSession session

    ) {

        if(bindingResult.hasErrors()){
            model.addAttribute("errorMessage","Invalid input. Please check your email and password.");
            return "member/login";
        }

        try {
            MemberEntity member = memberBusiness.login(request);
            session.setAttribute("successMessage", member); // 세션에 사용자 정보 저장
            model.addAttribute("successMessage", "Login successful!");

            return "redirect:/index"; // 성공 시 대시보드로 리다이렉트
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Invalid email or password. Please try again.");
            return "member/login"; // 실패 시 다시 로그인 페이지
        }
    }

}
