package toy.withme58.api.friend.dto;

import lombok.*;
import toy.withme58.db.friend.enums.FriendStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendResponse {

    private Long id;

    private String email;

    private String name;

    private FriendStatus status;
}
