package toy.withme58.api.memberfriend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toy.withme58.api.friend.dto.response.FriendResponse;
import toy.withme58.api.member.dto.response.MemberResponse;
import toy.withme58.db.memberfriend.enums.MemberFriendStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberFriendResponse {

    private Long id;

    private MemberResponse memberResponse;

    private FriendResponse friendResponse;

    private LocalDateTime createAt;

    private LocalDateTime registeredAt;

    private MemberFriendStatus status;
}
