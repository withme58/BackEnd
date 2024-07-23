package toy.withme58.api.answer.converter;

import toy.withme58.api.answer.dto.response.AnswerResponse;
import toy.withme58.api.common.annotation.Converter;
import toy.withme58.db.answer.AnswerEntity;

@Converter
public class AnswerConverter {



    public AnswerResponse toResponse(AnswerEntity entity){
        return AnswerResponse.builder()
                .id(entity.getId())
                .questionId(entity.getQuestion().getId())
                .senderId(entity.getSenderId())
                .receiverId(entity.getReceiverId())
                .content(entity.getContent())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .answeredAt(entity.getAnsweredAt())
                .build();
    }

}
