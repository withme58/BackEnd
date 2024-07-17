package toy.withme58.db.member.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MemberStatus {

    REGISTERED("등록"),
    UNREGISTERED("해제"),
    ;

    private String status;
}
