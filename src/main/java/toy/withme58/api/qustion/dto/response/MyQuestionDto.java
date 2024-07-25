package toy.withme58.api.qustion.dto.response;

import lombok.Builder;
import lombok.Getter;
import toy.withme58.api.qustion.dto.QuestionsDto;

import java.util.List;

@Builder
@Getter
public class MyQuestionDto {

    private List<QuestionsDto> question;
    private String status;
}
