package io.spring.websocket.member.repository;

import io.spring.websocket.member.dto.SignInRequest;
import io.spring.websocket.member.entity.Member;

public interface MemberQuerydslRepository {
    boolean checkDuplicatedUsername(String username);
    boolean checkDuplicatedNickname(String nickname);
    Member signIn(SignInRequest request);
}
