package com.sejong.health.controller;

import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final MemberRepository memberRepository;

    @GetMapping("/2")
    public String test() {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setEmail("kkd06144@naver.com");
        memberEntity.setPassword("123456");
        memberEntity.setNickName("dongho");

        memberRepository.save(memberEntity);
        memberRepository.findAll().forEach(System.out::println);
        return "fuck";

    }
}
