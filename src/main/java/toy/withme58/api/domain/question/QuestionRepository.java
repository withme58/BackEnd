package toy.withme58.api.domain.question;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.withme58.db.question.QuestionEntity;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    List<QuestionEntity> findAll();
}
