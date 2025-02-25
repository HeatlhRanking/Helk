package com.sejong.health.api.member.controller;

import com.sejong.health.api.member.business.MemberBusiness;
import com.sejong.health.api.member.dto.request.MemberLoginRequest;
import com.sejong.health.api.member.dto.request.MemberSignUpRequest;
import com.sejong.health.common.Member;
import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.member.MemberRepository;
import com.sejong.health.db.member.enums.MemberStatus;
import jakarta.annotation.PostConstruct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(true)
class MemberOpenApiControllerTest {

    @Autowired
    private MemberBusiness memberBusiness;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void Init(){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNickName("dongho");
        memberEntity.setPassword("1234");
        memberEntity.setEmail("kkd06144@naver.com");
        memberEntity.setStatus(MemberStatus.REGISTERED);
        memberRepository.save(memberEntity);
    }

    @Test
    void 로그인_하기() {
        Member member = memberBusiness.login(new MemberLoginRequest("kkd06144@naver.com", "1234"));

        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getNickName()).isEqualTo("dongho");
    }

    @Test
    void 회원가입_하기() {
        MemberSignUpRequest member = new MemberSignUpRequest("dongho","kkd06166@naver.com","1234");

        memberBusiness.signup(member);

       MemberEntity findMember = memberRepository
                .findFirstByEmailAndPasswordAndStatusOrderByIdDesc
                        ("kkd06166@naver.com", "1234", MemberStatus.REGISTERED).get();

       assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
       assertThat(findMember.getPassword()).isEqualTo(member.getPassword());

    }



}