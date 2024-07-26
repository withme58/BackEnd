package toy.withme58.api.qustion.dto.request;

import lombok.Getter;

@Getter
public class SendingAnswerRequest {
    private Long friendId; //질문 보낸 친구의 아이
    private String answer;
}
