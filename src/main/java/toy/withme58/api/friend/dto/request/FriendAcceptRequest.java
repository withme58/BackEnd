package toy.withme58.api.friend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendAcceptRequest {

    @NotBlank
    private String name;
}
