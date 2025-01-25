package com.sejong.health.db.question;

import com.sejong.health.db.answer.AnswerEntity;
import com.sejong.health.db.member.MemberEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String context;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MemberEntity member;

    @OneToMany(mappedBy = "question")
    private List<AnswerEntity> answers = new ArrayList<>();

    @Column(columnDefinition = "int default 0")
    private Integer likes;


    public void makeMember(MemberEntity member) {
        if (this.member == member) return;
        this.member = member;
        member.makeQuestion(this);

    }


    public void makeAnswer(AnswerEntity answer) {
        if(answers==null){
            answers = new ArrayList<>();
        }
        if (answer != null && !answers.contains(answer)) {
            answers.add(answer);
            answer.makeQuestion(this);
        }
    }

}
