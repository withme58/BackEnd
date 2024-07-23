package toy.withme58.api.home.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class FriendResponse {
    private String status;
    private List<MemberFriendDto> friends;
}
