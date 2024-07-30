package toy.withme58.api.qustion.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.withme58.api.common.annotation.MemberSession;
import toy.withme58.api.common.api.Api;
import toy.withme58.api.member.dto.Member;
import toy.withme58.api.qustion.business.QuestionBusiness;
import toy.withme58.api.qustion.dto.request.SendingAnswerRequest;
import toy.withme58.api.qustion.dto.response.MyQuestionResponse;
import toy.withme58.api.qustion.dto.response.SendingAnswerResponse;

@RequiredArgsConstructor
@RequestMapping("/question")
@RestController
public class QuestionController {

    private final QuestionBusiness questionBusiness;

    @GetMapping
    public Api<MyQuestionResponse> myQuestion(
            @Parameter(hidden = true)
            @MemberSession Member member) {
        MyQuestionResponse myQuestionResponse = questionBusiness.myQuestionResponse(member.getId());
        return Api.OK(myQuestionResponse);
    }

    @PostMapping
    public Api<SendingAnswerResponse> sendingAnswer(@RequestBody SendingAnswerRequest request) {
        SendingAnswerResponse sendingAnswerResponse = questionBusiness.sendingAnswer(request);
        return Api.OK(sendingAnswerResponse);
    }
}
