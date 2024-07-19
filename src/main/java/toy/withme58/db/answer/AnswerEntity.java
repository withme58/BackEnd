package toy.withme58.db.answer;

import jakarta.persistence.*;
import lombok.*;
import toy.withme58.db.answer.enums.AnswerStatus;

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

    //todo question_id 연관관계 추가해야 됨

    @Column(nullable = false)
    private Long senderId;

    @Column(nullable = false)
    private Long receiverId;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    @Enumerated(EnumType.STRING)
    private AnswerStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime answeredAt;
}
