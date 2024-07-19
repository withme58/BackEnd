package toy.withme58.db.answer.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AnswerStatus {

    REGISTERED("등록"),
    UNREGISTERED("해제"),
    ;


    private String status;
}
