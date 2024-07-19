package toy.withme58.db.question.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum QuestionStatus {

    REGISTERED("등록"),
    UNREGISTERED("해제"),
    ;

    private String status;
}
