package toy.withme58.api.friend.dto;

import lombok.*;
import toy.withme58.api.member.dto.response.MemberResponse;
import toy.withme58.db.friend.FriendEntity;
import toy.withme58.db.friend.enums.FriendStatus;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberFriendResponse {

    private MemberResponse memberResponse;

    private List<FriendResponse> friendResponseList;

}
