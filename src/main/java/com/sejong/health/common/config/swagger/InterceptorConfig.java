package com.sejong.health.common.config.swagger;

import com.sejong.health.common.Interceptor.SessionInterceptor;
import com.sejong.health.common.argument.LoginMemberArgumentResolver;
import com.sejong.health.db.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig {

    private final MemberRepository memberRepository;

    @Bean
    public SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor();
    }

    @Bean
    public LoginMemberArgumentResolver loginMemberArgumentResolver() {
        return new LoginMemberArgumentResolver(memberRepository);
    }

}
