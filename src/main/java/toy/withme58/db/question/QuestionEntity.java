package toy.withme58.db.question;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.withme58.db.memberfriend.MemberFriendEntity;
import toy.withme58.db.memberquestion.MemberQuestionEntity;
import toy.withme58.db.question.enums.QuestionStatus;

import java.util.List;

@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String colorCode;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionStatus status;

    @OneToMany(mappedBy = "question")
    private List<MemberQuestionEntity> memberQuestionList = List.of();
}
