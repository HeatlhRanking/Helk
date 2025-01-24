package com.sejong.health.db.ranking;

import com.sejong.health.db.base.BaseEntity;
import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.ranking.enums.RankingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="ranking")
public class RankingEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rankingValue;

    @Column(columnDefinition = "varchar(50)",nullable = false)
    @Enumerated(EnumType.STRING)
    private RankingStatus rankingStatus;

    @OneToOne(mappedBy = "ranking")
    private MemberEntity member;


    public void makeMember(MemberEntity member) {
        this.member = member;
        if (member != null&& member.getRanking()!=this) {
            member.makeRanking(this);
        }
    }

}
