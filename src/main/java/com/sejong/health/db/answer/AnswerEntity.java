package com.sejong.health.db.answer;

import com.sejong.health.db.base.BaseEntity;
import com.sejong.health.db.member.MemberEntity;
import com.sejong.health.db.question.QuestionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "answer")
public class AnswerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "int default 0")
    private Integer likes;

    @Column(length = 500, nullable = false)
    private String context;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "question_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private QuestionEntity question;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MemberEntity member;

    public void makeQuestion(QuestionEntity question) {
        if (this.question != question) {
            this.question = question;
        }

        question.makeAnswer(this);
    }

    public void makeMember(MemberEntity member) {
        if (this.member == member) return;

        this.member = member;
        member.makeAnswer(this);
    }

}
