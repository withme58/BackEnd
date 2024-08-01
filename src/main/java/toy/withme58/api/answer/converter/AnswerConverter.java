package toy.withme58.api.answer.converter;

import toy.withme58.api.answer.dto.response.AnswerInfoResponse;
import toy.withme58.api.answer.dto.response.AnswerResponse;
import toy.withme58.api.common.annotation.Converter;
import toy.withme58.db.answer.AnswerEntity;
import toy.withme58.db.question.QuestionEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Converter
public class AnswerConverter {

    public AnswerResponse toResponse(QuestionEntity questionEntity,
                                     String receiverName,
                                     LocalDateTime createdAt) {
        return AnswerResponse.builder()
                .questionId(questionEntity.getId())
                .receiverName(receiverName)
                .colorCode(questionEntity.getColorCode())
                .questionContent(questionEntity.getTitle())
                .createdAt(createdAt)
                .build();
    }

    public AnswerInfoResponse toResponse(AnswerEntity answerEntity,
                                     String receiverName,
                                     String senderName) {
        return AnswerInfoResponse.builder()
                .senderName(senderName)
                .receiverName(receiverName)
                .content(answerEntity.getContent())
                .questionTitle(answerEntity.getQuestion().getTitle())
                .createdAt(answerEntity.getCreatedAt())
                .answeredAt(answerEntity.getAnsweredAt())
                .build();
    }
}
