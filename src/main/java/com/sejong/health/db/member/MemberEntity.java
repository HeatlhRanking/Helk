package com.sejong.health.db.member;

import com.sejong.health.db.answer.AnswerEntity;
import com.sejong.health.db.base.BaseEntity;
import com.sejong.health.db.member.enums.MemberStatus;
import com.sejong.health.db.question.QuestionEntity;
import com.sejong.health.db.ranking.RankingEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "member")
public class MemberEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    private String nickName;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinColumn(name = "ranking_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private RankingEntity ranking;

    @OneToMany(mappedBy = "member")
    private List<QuestionEntity> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<AnswerEntity> answers;

    public void makeRanking(RankingEntity ranking) {
        this.ranking = ranking;
        if (ranking != null && ranking.getMember() != this) {
            ranking.makeMember(this);
        }
    }

    public void makeQuestion(QuestionEntity question) {
        if (questions == null) {
            questions = new ArrayList<>();
        }

        if (question != null && !questions.contains(question)) {
            this.questions.add(question);
            question.makeMember(this);
        }
    }

    public void makeAnswer(AnswerEntity answer) {
        if(answers==null){
            answers = new ArrayList<>();
        }

        if(answer!=null && !answers.contains(answer)){
            this.answers.add(answer);
            answer.makeMember(this);
        }
    }

}
