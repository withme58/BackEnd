package toy.withme58.api.answer.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toy.withme58.api.answer.business.AnswerBusiness;
import toy.withme58.api.answer.dto.response.AnswerInfoResponse;
import toy.withme58.api.answer.dto.response.AnswerResponse;
import toy.withme58.api.common.annotation.MemberSession;
import toy.withme58.api.common.api.Api;
import toy.withme58.api.member.dto.Member;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
@RequiredArgsConstructor
public class AnswerApiController {

    private final AnswerBusiness answerBusiness;

    @GetMapping("/list")
    public Api<List<AnswerResponse>> showAllList(
            @Parameter(hidden = true)
            @MemberSession Member member
    ){
        List<AnswerResponse> responseList = answerBusiness.getAllList(member);

        return Api.OK(responseList);
    }

    @GetMapping("/one")
    public Api<AnswerInfoResponse> showOne(
            @Parameter(hidden = true)
            @MemberSession Member member,

            @RequestParam("questionId") Long questionId
    ){
        AnswerInfoResponse response = answerBusiness.getOneByQuestionId(member,questionId);

        return Api.OK(response);
    }
}
