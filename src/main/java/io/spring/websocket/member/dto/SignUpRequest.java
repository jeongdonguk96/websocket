package io.spring.websocket.member.dto;

import io.spring.websocket.member.entity.Member;
import jakarta.validation.constraints.NotNull;

public record SignUpRequest(
    @NotNull
    String username,
    @NotNull
    String password,
    @NotNull
    String nickname
) {

    public Member toEntity(SignUpRequest request) {
        return Member.signUpBuilder()
                .request(request)
                .build();
    }
}
