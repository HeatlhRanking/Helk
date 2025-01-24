package com.sejong.health.db.member;

import com.sejong.health.db.base.BaseEntity;
import com.sejong.health.db.member.enums.MemberStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name= "member")
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50 , nullable = false)
    private String password;

    @Column(length=50, nullable = false)
    private String nickName;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

}
