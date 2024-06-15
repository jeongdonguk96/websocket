package io.spring.websocket.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.spring.websocket.member.dto.SignInRequest;
import io.spring.websocket.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static io.spring.websocket.member.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberQuerydslRepositoryImpl implements MemberQuerydslRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    public boolean checkDuplicatedUsername(String username) {
        Integer result = queryFactory.selectOne()
                .from(member)
                .where(member.username.eq(username))
                .fetchFirst();

        return result != null;
    }

    @Override
    public boolean checkDuplicatedNickname(String nickname) {
        Integer result = queryFactory.selectOne()
                .from(member)
                .where(member.nickname.eq(nickname))
                .fetchFirst();

        return result != null;
    }

    @Override
    public Member signIn(SignInRequest request) {
        return queryFactory.select(member)
                .from(member)
                .where(member.username.eq(request.username())
                        .and(member.password.eq(request.password())))
                .fetchFirst();
    }

}
