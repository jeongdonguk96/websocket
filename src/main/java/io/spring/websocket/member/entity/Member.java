package io.spring.websocket.member.entity;

import io.spring.websocket.member.dto.SignUpRequest;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "member2")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String nickname;


    @Builder(builderMethodName = "signUpBuilder")
    public Member(SignUpRequest request) {
        this.username = request.username();
        this.password = request.password();
        this.nickname = request.nickname();
    }

}
