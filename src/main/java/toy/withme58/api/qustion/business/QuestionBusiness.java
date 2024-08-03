package toy.withme58.api.qustion.business;

import lombok.RequiredArgsConstructor;
import toy.withme58.api.common.annotation.Business;
import toy.withme58.api.qustion.converter.QuestionConverter;
import toy.withme58.api.qustion.dto.QuestionsDto;
import toy.withme58.api.qustion.dto.request.SendingAnswerRequest;
import toy.withme58.api.qustion.dto.response.MyQuestionResponse;
import toy.withme58.api.qustion.dto.response.OneQuestionResponse;
import toy.withme58.api.qustion.dto.response.SendingAnswerResponse;
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
                    String questionTitle = questionService.findQuestionTitle(q.getQuestion().getId());
                    String senderName = questionService.findFriendNameBySenderId(q.getSenderId());
                    return questionConverter.questionsDto(questionTitle, senderName, q);
                }).toList();

        return questionConverter.questionResponse(questionsDto);
    }

    public SendingAnswerResponse sendingAnswer(SendingAnswerRequest request) {//senderId는 질문한사람!!답변은 받는 것
        questionService.updateAnswer(request.getAnswerId(), request.getAnswer());
        AnswerEntity answerEntity = questionService.findAnswerById(request.getAnswerId());
        return questionConverter.sendingAnswerResponse(answerEntity);
    }

    public OneQuestionResponse oneQuestionResponse(Long answerId) {
        AnswerEntity answerEntity = questionService.findAnswerById(answerId);
        String questionTitle = answerEntity.getQuestion().getTitle();
        String senderName = questionService.findFriendNameBySenderId(answerEntity.getSenderId());
        return questionConverter.oneQuestionResponse(questionTitle, senderName, answerEntity);
    }
}
