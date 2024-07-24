package toy.withme58.api.home.controller;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.withme58.api.common.annotation.MemberSession;
import toy.withme58.api.common.api.Api;
import toy.withme58.api.home.business.HomeBusiness;
import toy.withme58.api.home.dto.request.SendQuestionRequestDto;
import toy.withme58.api.home.dto.response.HomeFriendResponse;
import toy.withme58.api.home.dto.response.HomeResponse;
import toy.withme58.api.home.dto.response.SendQuestionResponse;
import toy.withme58.api.member.dto.Member;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class HomeController {

    private final HomeBusiness homeBusiness;

    @GetMapping
    public Api<HomeResponse> home(
            @Parameter(hidden = true)
            @MemberSession Member member) {
        HomeResponse homeResponse = homeBusiness.homeResponse(member.getId());
        return Api.OK(homeResponse);
    }

    @GetMapping("/myfriends")
    public Api<HomeFriendResponse> friendResponseApi(
            @Parameter(hidden = true)
            @MemberSession Member member) {
        HomeFriendResponse homeFriendResponse = homeBusiness.friendResponse(member.getId());
        return Api.OK(homeFriendResponse);
    }

    @PostMapping("/myfriends")
    public Api<SendQuestionResponse> sendQuestion(
            @Parameter(hidden = true)
            @MemberSession Member member,
            @RequestBody SendQuestionRequestDto requestDto
    ) {
        Long receiverId = homeBusiness.findReceiverIdByFriendName(requestDto.getFriendName());
        Long senderId = homeBusiness.findSenderId(member.getId());
        homeBusiness.saveQuestion(senderId, receiverId, requestDto.getQuestionId());
        SendQuestionResponse sendQuestionResponse = homeBusiness.sendQuestionResponse();
        return Api.OK(sendQuestionResponse);
    }
}
