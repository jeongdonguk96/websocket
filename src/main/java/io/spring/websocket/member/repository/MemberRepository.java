package io.spring.websocket.member.repository;

import io.spring.websocket.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQuerydslRepository {
}
