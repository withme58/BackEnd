package toy.withme58.api.home.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import toy.withme58.db.question.QuestionEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class SendQuestionDto {
    private QuestionEntity question;
    private LocalDateTime createAt;
    private Long receiverId;
    private Long senderId;
}
