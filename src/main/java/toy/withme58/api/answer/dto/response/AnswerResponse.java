package toy.withme58.api.answer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toy.withme58.db.answer.enums.AnswerStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResponse {

    private Long id;

    private Long questionId;

    private Long senderId;

    private Long receiverId;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime answeredAt;

    private AnswerStatus status;
}
