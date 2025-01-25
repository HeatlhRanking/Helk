package com.sejong.health.api.question.controller;

import com.sejong.health.api.question.business.QuestionBusiness;
import com.sejong.health.api.question.dto.request.PageParamRequest;
import com.sejong.health.api.question.dto.response.PageParamResponse;
import com.sejong.health.api.question.dto.response.QuestionResponse;
import com.sejong.health.db.question.QuestionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionBusiness questionBusiness;

    @GetMapping("/all")
    public String allQuestions(@ModelAttribute PageParamRequest pageParamRequest, Model model) {
        PageParamResponse<QuestionEntity> questionsPage = questionBusiness.getQuestionsPage(pageParamRequest);
        model.addAttribute("questionsPage",questionsPage);

        return "/community/community";
    }

    @GetMapping("/showOne/{questionId}")
    public String oneQuestion(@PathVariable("questionId") Long questionId, Model model) {
        System.out.println("fuck");
        QuestionResponse question = questionBusiness.getQuestionOne(questionId);
        model.addAttribute("question",question);
        return "/community/question";
    }
}
