package io.spring.websocket.member.dto;

import jakarta.validation.constraints.NotNull;

public record SignInRequest(
    @NotNull
    String username,
    @NotNull
    String password
) {
}
