package io.spring.websocket.member.service;

import io.spring.websocket.exception.MemberException;
import io.spring.websocket.member.dto.SignInRequest;
import io.spring.websocket.member.dto.SignUpRequest;
import io.spring.websocket.member.entity.Member;
import io.spring.websocket.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public Member signUp(SignUpRequest request) {
        checkDuplication(request);
        Member newMember = request.toEntity(request);
        return memberRepository.save(newMember);
    }


    @Transactional(readOnly = true)
    public Member signIn(SignInRequest request, HttpSession session) {
        Member member = memberRepository.signIn(request);
        if (member == null) {
            throw new MemberException("입력한 정보가 일치하지 않습니다.");
        }

        session.setAttribute("member", member);
        return member;
    }


    private void checkDuplication(SignUpRequest request) {
        boolean isUsernameDuplicated = memberRepository.checkDuplicatedUsername(request.username());
        if (isUsernameDuplicated) {
            throw new MemberException("중복된 username이 있습니다.");
        }

        boolean isNicknameDuplicated = memberRepository.checkDuplicatedNickname(request.nickname());
        if (isNicknameDuplicated) {
            throw new MemberException("중복된 nickname이 있습니다.");
        }
    }

}
