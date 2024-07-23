package toy.withme58.db.memberfriend.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MemberFriendStatus {

    REGISTERED("친구 등록"),
    UNREGISTERED("친구 등록 거절"),
    WAITING("친구 등록 대기중"),
    DELETED("친구 삭제"),
    ;

    private String status;
}

