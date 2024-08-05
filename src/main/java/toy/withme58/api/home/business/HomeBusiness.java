package toy.withme58.api.home.business;

import lombok.RequiredArgsConstructor;
import toy.withme58.api.answer.service.AnswerService;
import toy.withme58.api.common.annotation.Business;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.api.home.converter.HomeConverter;
import toy.withme58.api.home.dto.response.*;
import toy.withme58.api.home.service.HomeService;
import toy.withme58.db.answer.AnswerEntity;
import toy.withme58.db.member.MemberEntity;
import toy.withme58.db.member.MemberRepository;
import toy.withme58.db.question.QuestionEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Business
@RequiredArgsConstructor
public class HomeBusiness {

    private final HomeConverter homeConverter;
    private final HomeService homeService;
    private final MemberRepository memberRepository;
    private final AnswerService answerService;

    public HomeResponse homeResponse(Long memberId) {
        QuestionEntity question = homeService.findQuestion(memberId); //질문내용
//        QuestionEntity questionEntity = homeService.findQuestionId(question);
        MemberEntity member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT)); //멤버 조회
        LocalDateTime createdAt = member.getCreatedAt(); //멤버 가입한 시기
        return homeConverter.homeResponse(question, createdAt);
    }

    public HomeFriendResponse friendResponse(Long memberId) {
        List<MemberFriendDto> memberFriendEntity = homeService.findMemberFriendEntity(memberId);
        return homeConverter.friendResponse(memberFriendEntity);
    }

    public SendQuestionResponse sendQuestionResponse(String friendName, Long memberId, Long questionId) {
        Long receiverId = homeService.findReceiverIdByFriendName(friendName);
        Long senderId = homeService.findSenderId(memberId);

        validateGiveOneQuestion(senderId);

        SendQuestionDto sendQuestionDto = homeService.makeSendQuestion(senderId, receiverId, questionId);
        AnswerEntity answer = homeConverter.sendQuestionAnswer(sendQuestionDto);

        homeService.saveQuestion(answer); //DB에 저장 수행
        return homeConverter.sendQuestionResponse();
    }

    private void validateGiveOneQuestion(Long senderId){

        Optional<AnswerEntity> answerEntity = answerService.getOneBySenderIdAndCreatedAt(senderId, LocalDate.now());

        if(answerEntity.isPresent()) {
            throw new ApiException(ErrorCode.MULTI_REQUEST);
        }
    }
}
