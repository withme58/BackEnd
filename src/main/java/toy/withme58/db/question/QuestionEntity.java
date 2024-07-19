package toy.withme58.db.question;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import toy.withme58.db.question.enums.QuestionStatus;

@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

//    @OneToMany(mappedBy = "member")
//    private List<MemberFriendEntity> memberFriendList = List.of();
}
