package toy.withme58.api.qustion.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SendingAnswerResponse {
    private String answer;
    private String question;
    private String status;
}
