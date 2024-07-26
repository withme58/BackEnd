package toy.withme58.api.qustion.business;

import lombok.RequiredArgsConstructor;
import toy.withme58.api.common.annotation.Business;
import toy.withme58.api.qustion.converter.QuestionConverter;
import toy.withme58.api.qustion.dto.QuestionsDto;
import toy.withme58.api.qustion.dto.response.MyQuestionResponse;
import toy.withme58.api.qustion.service.QuestionService;
import toy.withme58.db.answer.AnswerEntity;

import java.util.List;

@RequiredArgsConstructor
@Business
public class QuestionBusiness {

    private final QuestionService questionService;
    private final QuestionConverter questionConverter;

    public MyQuestionResponse myQuestionResponse(Long memberId) {
        List<AnswerEntity> questions = questionService.findQuestionsByReceiverId(memberId);

        List<QuestionsDto> questionsDto = questions.stream()
                .map(q -> {
                    String questionTitle = questionService.findQuestionTitle(q.getId());
                    String senderName = questionService.findFriendNameBySenderId(q.getSenderId());
                    return questionConverter.questionsDto(questionTitle, senderName, q);
                }).toList();

        return questionConverter.questionResponse(questionsDto);
    }
}
