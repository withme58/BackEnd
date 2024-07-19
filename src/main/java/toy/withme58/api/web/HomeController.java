package toy.withme58.api.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.withme58.api.domain.question.QuestionService;
import toy.withme58.db.question.QuestionEntity;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final QuestionService questionService;

    @GetMapping("/")
    public ResponseEntity home() {
        List<QuestionEntity> questions = questionService.findAll();
        return ResponseEntity.ok(questions);
    }
}
