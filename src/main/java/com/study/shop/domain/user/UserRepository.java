package com.study.shop.domain.user;

import com.study.shop.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { // Long은 User테이블의 PK의 타입
    // 이메일로 회원 정보 조회 (select * from shop.user where email=?
    Optional<User> findByEmail(String email);
}