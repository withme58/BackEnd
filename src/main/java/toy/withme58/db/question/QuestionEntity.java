package toy.withme58.db.question;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Table(name = "question")
@NoArgsConstructor
@Entity
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;


}
