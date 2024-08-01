package toy.withme58.api.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import toy.withme58.db.member.enums.MemberStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponse {

    private Long id;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private MemberStatus status;

}
