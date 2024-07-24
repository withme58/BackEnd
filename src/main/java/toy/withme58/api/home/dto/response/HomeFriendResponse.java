package toy.withme58.api.home.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class HomeFriendResponse {
    private String status;
    private List<MemberFriendDto> friends;
}
