package toy.withme58.api.answer.business;

import lombok.RequiredArgsConstructor;
import toy.withme58.api.answer.converter.AnswerConverter;
import toy.withme58.api.answer.dto.response.AnswerResponse;
import toy.withme58.api.answer.service.AnswerService;
import toy.withme58.api.common.annotation.Business;
import toy.withme58.api.member.dto.Member;

import java.util.List;

@Business
@RequiredArgsConstructor
public class AnswerBusiness {

    private final AnswerConverter answerConverter;
    private final AnswerService answerService;


    public List<AnswerResponse> getAllList(Member member) {

        var answerList = answerService.getAllListByReceiverId(member.getId());

        var answerResponseList = answerList.stream()
                .map(answerConverter::toResponse)
                .toList();

        return answerResponseList;
    }

    public AnswerResponse getOneByQuestionId(Member member, Long questionId) {

        var answerEntity = answerService.getOneByReceiverIdAndQuestionId(member.getId(),questionId);

        var answerResponse = answerConverter.toResponse(answerEntity);

        return answerResponse;
    }
}
