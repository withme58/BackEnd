package toy.withme58.api.qustion.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class OneQuestionResponse {
    private LocalDateTime createdAt;
    private String questionName;
    private String friendName;
    private String status;
}
