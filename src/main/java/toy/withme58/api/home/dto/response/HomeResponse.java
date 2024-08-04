package toy.withme58.api.home.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class HomeResponse {
    private Long questionId;
    private String question;
    private LocalDateTime createdAt;
    private String status;
}
