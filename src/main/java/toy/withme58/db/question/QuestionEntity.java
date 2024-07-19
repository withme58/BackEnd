package toy.withme58.db.question;

import jakarta.persistence.*;

@Table(name = "question")
@Entity
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;
}
