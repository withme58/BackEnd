package toy.withme58.db.question;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.withme58.db.answer.enums.AnswerStatus;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findAll();

    //select * from questionEntity where id and status = ? order by id desc

    Optional<QuestionEntity> findFirstByIdAndStatusOrderById(Long id, AnswerStatus status);

    Optional<QuestionEntity> findFirstByTitle(String title);
}
