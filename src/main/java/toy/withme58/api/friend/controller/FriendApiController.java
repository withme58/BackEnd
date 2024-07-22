package toy.withme58.api.friend.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.withme58.api.common.annotation.MemberSession;
import toy.withme58.api.common.api.Api;
import toy.withme58.api.friend.business.FriendBusiness;
import toy.withme58.api.friend.dto.request.FriendAcceptRequest;
import toy.withme58.api.friend.dto.response.FriendResponse;
import toy.withme58.api.friend.dto.response.MemberFriendResponse;
import toy.withme58.api.member.dto.Member;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class FriendApiController {

    private final FriendBusiness friendBusiness;

    @GetMapping("/friends")
    public Api<MemberFriendResponse> showFriendList(
            @MemberSession Member member
    ) {
        var response = friendBusiness.getFriendsByMember(member);
        return Api.OK(response);
    }

    @PostMapping("/friend/accept")
    public Api<FriendResponse> acceptFriend(
            @Valid @RequestParam
            FriendAcceptRequest request,

            @Parameter(hidden=true)
            @MemberSession Member member
    ){
        var response = friendBusiness.acceptFriendByFriendName(request, member);

        return Api.OK(response);
    }


}
