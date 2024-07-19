package toy.withme58.db.friend.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FriendStatus {

    REGISTERED("등록"),
    UNREGISTERED("해제"),
    ;

    private String status;

}
