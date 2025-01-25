package com.sejong.health.common.argument;

import com.sejong.health.common.Member;
import com.sejong.health.common.annotation.LoginMember;
import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.member.MemberRepository;
import com.sejong.health.db.member.enums.MemberStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@RequiredArgsConstructor
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        parameter.getParameterAnnotation(LoginMember.class);
        return true;

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer
            , NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        if (session != null) {
            Long memberId = (Long) session.getAttribute("sessionId");
            MemberEntity memberEntity = memberRepository.findById(memberId)
                    .orElseThrow(()-> new RuntimeException("session error"));

            return Member.builder()
                    .id(memberEntity.getId())
                    .email(memberEntity.getEmail())
                    .nickName(memberEntity.getNickName())
                    .ranking(memberEntity.getRanking())
                    .status(MemberStatus.REGISTERED)
                    .build();
        }
        return null;
    }
}
