package com.sejong.health.api.member.business;

import com.sejong.health.api.common.Business;
import com.sejong.health.api.member.dto.request.MemberLoginRequest;
import com.sejong.health.api.member.service.MemberService;
import com.sejong.health.db.member.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Business
@RequiredArgsConstructor
public class MemberBusiness {

    private final MemberService memberService;

    public MemberEntity login(MemberLoginRequest request){

        return memberService.login(request.getEmail(), request.getPassword());
    }

}
