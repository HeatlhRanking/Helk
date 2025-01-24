package com.sejong.health.db.member;

import com.sejong.health.db.member.enums.MemberStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

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
    void memberEntity_조회() {

        MemberEntity memberEntity = memberRepository.findById(1L).get();

        assertThat(memberEntity.getNickName()).isEqualTo("dongho");
        assertThat(memberEntity.getEmail()).isEqualTo("kkd06144@naver.com");
        assertThat(memberEntity.getPassword()).isEqualTo("1234");
        assertThat(memberEntity.getStatus()).isEqualTo(MemberStatus.REGISTERED);

    }

    @Test
    void memberEntity_삭제() {
        MemberEntity memberEntity = memberRepository.findById(1L).get();
        memberRepository.delete(memberEntity);

        boolean exists = memberRepository.existsById(1L);
        assertThat(exists).isFalse();
    }




}