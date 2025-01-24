package com.sejong.health.db.member;

import com.sejong.health.db.member.enums.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    //select * from user where email =? and password = ? and status = ? order by id desc limit 1
    Optional<MemberEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, MemberStatus staus);

}
