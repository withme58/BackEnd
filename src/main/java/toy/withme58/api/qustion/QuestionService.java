package toy.withme58.api.qustion;

import org.springframework.stereotype.Service;
import toy.withme58.db.question.QuestionEntity;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<QuestionEntity> findAll() {
        return questionRepository.findAll();
    }
}
