package toy.withme58.api.home.converter;

import toy.withme58.api.common.annotation.Converter;
import toy.withme58.api.home.dto.response.*;
import toy.withme58.db.answer.AnswerEntity;
import toy.withme58.db.answer.enums.AnswerStatus;
import toy.withme58.db.member.enums.MemberStatus;
import toy.withme58.db.memberfriend.enums.MemberFriendStatus;

import java.time.LocalDateTime;
import java.util.List;

@Converter
public class HomeConverter {

    public HomeResponse homeResponse(String question, LocalDateTime createdAt) {
        return HomeResponse.builder()
                .question(question)
                .createdAt(createdAt)
                .status(MemberStatus.REGISTERED.getStatus()).build();
    }

    public HomeFriendResponse friendResponse(List<MemberFriendDto> dto) {
        return HomeFriendResponse.builder()
                .friends(dto)
                .status(MemberFriendStatus.REGISTERED.getStatus()).build();
    }

    public SendQuestionResponse sendQuestionResponse() {
        return SendQuestionResponse.builder()
                .status(AnswerStatus.UNREGISTERED.getStatus()).build();
    }

    public AnswerEntity sendQuestionAnswer(SendQuestionDto sendQuestionDto) {
        return AnswerEntity.builder().question(sendQuestionDto.getQuestion())
                .createdAt(sendQuestionDto.getCreateAt()).receiverId(sendQuestionDto.getReceiverId())
                .senderId(sendQuestionDto.getSenderId())
                .status(AnswerStatus.UNREGISTERED)
                .build();
    }
}
