package com.sejong.health.api.home;

import com.sejong.health.db.member.MemberEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        MemberEntity member = (MemberEntity)session.getAttribute("sessionId");

        if (member != null) {
            model.addAttribute("successMessage", member.getNickName() +" Success");
        }

        return "index";

    }
}
