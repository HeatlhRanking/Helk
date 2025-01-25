package com.sejong.health.api.home;

import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final MemberRepository memberRepository;

    public MemberEntity getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new RuntimeException("home controller error"));
    }
}
