package toy.withme58.api.answer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerInfoResponse {

    private String questionTitle;

    private String senderName;

    private String receiverName;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime answeredAt;
}
