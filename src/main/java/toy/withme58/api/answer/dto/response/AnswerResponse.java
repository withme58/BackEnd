package toy.withme58.api.answer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toy.withme58.db.answer.enums.AnswerStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResponse {

    private Long questionId;

    private String questionContent;

    private String colorCode;

    private LocalDateTime createdAt;

    private String receiverName;

}
