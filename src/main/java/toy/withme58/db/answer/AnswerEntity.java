package toy.withme58.db.answer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import toy.withme58.db.answer.enums.AnswerStatus;
import toy.withme58.db.question.QuestionEntity;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "answer")
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @Column(nullable = false)
    private Long senderId;

    @Column(nullable = false)
    private Long receiverId;

    private String content;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    @Enumerated(EnumType.STRING)
    private AnswerStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    //    @Column(nullable = false)
    private LocalDateTime answeredAt;

    public void setAnsweredAt() {
        this.answeredAt = LocalDateTime.now();
    }

    public void setAnswerContent(String answerContent) {
        this.content = answerContent;
    }

    public void setAnswerStatus(AnswerStatus status) {
        this.status = status;
    }
}
