package toy.withme58.db.memberquestion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import toy.withme58.db.member.MemberEntity;
import toy.withme58.db.question.QuestionEntity;

import java.time.LocalDateTime;

@NoArgsConstructor
@Table(name = "member_question")
@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
public class MemberQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id")
    private QuestionEntity question;
}
