package toy.withme58.db.answer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import toy.withme58.db.answer.enums.AnswerStatus;
import toy.withme58.db.question.QuestionEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name ="answer")
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

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    @Enumerated(EnumType.STRING)
    private AnswerStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

//    @Column(nullable = false)
    private LocalDateTime answeredAt;
}
