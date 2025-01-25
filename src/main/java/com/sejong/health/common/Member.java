package com.sejong.health.common;

import com.sejong.health.db.member.enums.MemberStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    private Long id;

    private String email;

    private String nickName;

    private MemberStatus status;
}
