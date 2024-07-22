package toy.withme58.api.friend.dto.response;

import lombok.*;
import toy.withme58.api.member.dto.response.MemberResponse;

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
