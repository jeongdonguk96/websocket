package io.spring.websocket.member.controller;

import io.spring.websocket.member.dto.SignInRequest;
import io.spring.websocket.member.dto.SignUpRequest;
import io.spring.websocket.member.service.MemberService;
import io.spring.websocket.response.dto.CommonResult;
import io.spring.websocket.response.service.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member", description = "회원")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ResponseService responseService;


    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public CommonResult signUp(@Validated @RequestBody SignUpRequest request) {
        return responseService.getSingleResult(memberService.signUp(request));
    }


    @Operation(summary = "로그인")
    @PostMapping("/signin")
    public CommonResult signIn(@Validated @RequestBody SignInRequest request) {
        return responseService.getSingleResult(memberService.signIn(request));
    }

}
