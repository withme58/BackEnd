package toy.withme58.api.answer.business;

import lombok.RequiredArgsConstructor;
import toy.withme58.api.answer.converter.AnswerConverter;
import toy.withme58.api.answer.dto.response.AnswerInfoResponse;
import toy.withme58.api.answer.dto.response.AnswerResponse;
import toy.withme58.api.answer.service.AnswerService;
import toy.withme58.api.common.annotation.Business;
import toy.withme58.api.common.annotation.MemberSession;
import toy.withme58.api.member.dto.Member;
import toy.withme58.api.member.service.MemberService;
import toy.withme58.api.qustion.service.QuestionService;
import toy.withme58.db.question.QuestionEntity;

import java.util.ArrayList;
import java.util.List;

@Business
@RequiredArgsConstructor
public class AnswerBusiness {

    private final AnswerConverter answerConverter;
    private final AnswerService answerService;

    private final MemberService memberService;

    public List<AnswerResponse> getAllList(Member member) {

        var answerList = answerService.getAllListBySenderId(member.getId());

        var answerResponseList = answerList.stream()
                .filter(it -> it.getContent() != null)
                .map(it -> {
                    var receiverName = memberService.getMember(it.getReceiverId()).getName();
                    return answerConverter.toResponse(it, receiverName);
                }).toList();

        return answerResponseList;
    }

    public AnswerInfoResponse getOneByQuestionId(Member member, Long answerId) {

        var answerEntity = answerService.getOneBySenderIdAndQuestionId(answerId);

        var receiverName = memberService.getMember(answerEntity.getReceiverId()).getName();
        var senderName = memberService.getMember(answerEntity.getSenderId()).getName();

        var answerResponse = answerConverter.toResponse(answerEntity,receiverName,senderName);

        return answerResponse;
    }
}
