package toy.withme58.db.answer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnswerStatus {

    REGISTERED("등록"),
    UNREGISTERED("미등록"),
    ;


    private String status;
}
