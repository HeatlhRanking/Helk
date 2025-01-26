package com.sejong.health.api.answer.controller;

import com.sejong.health.api.answer.business.AnswerBusiness;
import com.sejong.health.api.answer.dto.request.AnswerFormRequest;
import com.sejong.health.api.answer.dto.response.AnswerFormResponse;
import com.sejong.health.common.Member;
import com.sejong.health.common.annotation.LoginMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerBusiness answerBusiness;

    @GetMapping("/make/answer/{questionId}")
    public String makeAnswer(
            @PathVariable(name = "questionId") Long questionId,
            Model model) {

        model.addAttribute("questionId", questionId);
        return "community/answerForm";
    }

    @PostMapping("/make/answer/{questionId}")
    public String makeAnswer(@Valid @ModelAttribute AnswerFormRequest answerFormRequest,
                             @PathVariable(name = "questionId") Long questionId,
                             @LoginMember Member member) {

        AnswerFormResponse answerFormResponse = answerBusiness.makeAnswer(answerFormRequest, questionId, member.getId());

        return "redirect:/api/question/showOne/{questionId}";
    }
}
