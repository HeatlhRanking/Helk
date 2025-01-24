package com.sejong.health.api.member.converter;


import com.sejong.health.common.annotation.Converter;
import com.sejong.health.api.member.dto.request.MemberSignUpRequest;
import com.sejong.health.db.member.MemberEntity;

import java.util.Optional;

@Converter
public class MemberConverter {


    public MemberEntity toMemberEntity(MemberSignUpRequest request){
            return Optional.ofNullable(request)
                    .map(it->{
                        return MemberEntity.builder()
                                .nickName(request.getNickName())
                                .email(request.getEmail())
                                .password(request.getPassword())
                                .build();
                    })
                    .orElseThrow(()-> new RuntimeException("toMemberEntity"));
    }
}
