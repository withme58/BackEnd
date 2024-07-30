package toy.withme58.db.question.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionStatus {

    REGISTERED("등록"),
    UNREGISTERED("해제"),
    ;

    private String status;
}
