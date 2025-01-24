package com.sejong.health.db.member;

import com.sejong.health.db.member.enums.MemberStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberEntityTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    void 멤버_엔티티가_작동하는지() {
        // 엔티티 생성
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNickName("dongho");
        memberEntity.setPassword("1234");
        memberEntity.setEmail("kkd06155@naver.com");
        memberEntity.setStatus(MemberStatus.REGISTERED);
        // 엔티티 저장
        memberRepository.save(memberEntity);

        // 데이터 조회
        List<MemberEntity> all = memberRepository.findAll();

        // 검증
        Assertions.assertThat(all.size()).isEqualTo(1);
        Assertions.assertThat(all.get(0).getNickName()).isEqualTo("dongho");
    }
}
