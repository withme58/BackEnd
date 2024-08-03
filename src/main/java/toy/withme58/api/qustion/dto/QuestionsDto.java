package toy.withme58.api.qustion.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class QuestionsDto {
    private String questionName;
    private String friendName;
    private Long friendId;
    private Long answerId;
    private LocalDateTime createdAt;
}
