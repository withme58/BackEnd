package toy.withme58.api.member.dto;


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
public class Member {

    private Long id;

    private String name;

    private String password;

    private String email;

    private MemberStatus status;

    private LocalDateTime createdAt;

}
