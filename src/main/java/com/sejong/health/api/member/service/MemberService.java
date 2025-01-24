package com.sejong.health.api.member.service;

import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.member.MemberRepository;
import com.sejong.health.db.member.enums.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberEntity signup(MemberEntity memberEntity) {
        return Optional.ofNullable(memberEntity)
                .map(it->{
                    it.setStatus(MemberStatus.REGISTERED);
                    return memberRepository.save(it);
                }).orElseThrow(()->new RuntimeException("register"));
    }

    public MemberEntity login(String email, String password){
        return getMember(email, password);
    }


    private MemberEntity getMember(String email, String password) {

      return memberRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(email, password, MemberStatus.REGISTERED)
              .orElseThrow(()-> new RuntimeException("getMember"));

    }


}
