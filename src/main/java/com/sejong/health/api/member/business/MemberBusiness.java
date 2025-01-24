package com.sejong.health.api.member.business;

import com.sejong.health.common.annotation.Business;
import com.sejong.health.api.member.converter.MemberConverter;
import com.sejong.health.api.member.dto.request.MemberLoginRequest;
import com.sejong.health.api.member.dto.request.MemberSignUpRequest;
import com.sejong.health.api.member.service.MemberService;
import com.sejong.health.db.member.MemberEntity;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class MemberBusiness {

    private final MemberService memberService;
    private final MemberConverter memberConverter;

    public MemberEntity login(MemberLoginRequest request){

        return memberService.login(request.getEmail(), request.getPassword());
    }

    public void signup(MemberSignUpRequest request){

        MemberEntity memberEntity = memberConverter.toMemberEntity(request);
        memberService.signup(memberEntity);

    }

}
