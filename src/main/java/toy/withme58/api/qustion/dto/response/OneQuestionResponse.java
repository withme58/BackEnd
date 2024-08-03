package toy.withme58.api.qustion.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OneQuestionResponse {
    private String questionName;
    private String friendName;
    private String status;
}
