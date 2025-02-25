package com.sejong.health.api.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignUpRequest {

    @NotBlank
    private String nickName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
