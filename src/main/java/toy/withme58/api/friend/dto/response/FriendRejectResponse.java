package toy.withme58.api.friend.dto.response;

import lombok.*;
import toy.withme58.db.friend.enums.FriendStatus;
import toy.withme58.db.memberfriend.enums.MemberFriendStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendRejectResponse {

    private Long id;

    private String email;

    private String name;

    private MemberFriendStatus status;
}
