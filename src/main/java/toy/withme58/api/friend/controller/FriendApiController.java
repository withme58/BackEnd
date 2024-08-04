package toy.withme58.api.friend.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import toy.withme58.api.common.annotation.MemberSession;
import toy.withme58.api.common.api.Api;
import toy.withme58.api.friend.business.FriendBusiness;
import toy.withme58.api.friend.dto.request.FriendAcceptRequest;
import toy.withme58.api.friend.dto.response.FriendResponse;
import toy.withme58.api.friend.dto.response.FriendsResponse;
import toy.withme58.api.member.dto.Member;
import toy.withme58.api.memberfriend.dto.response.MemberFriendResponse;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class FriendApiController {

    private final FriendBusiness friendBusiness;

    @GetMapping("/friends")
    public Api<FriendsResponse> showFriendList(
            @Parameter(hidden = true)
            @MemberSession Member member
    ) {
        var response = friendBusiness.getFriendsByMember(member);
        return Api.OK(response);
    }

    @GetMapping("/friend/waiting")
    public Api<FriendsResponse> showFriendWaitingList(
            @Parameter(hidden = true)
            @MemberSession Member member
    ){
        var response = friendBusiness.getFriendWaitingByMember(member);
        return Api.OK(response);
    }

    @PostMapping("/friend/request")
    public Api<MemberFriendResponse> requestFriend(
            @Valid @RequestBody
            FriendAcceptRequest request,

            @Parameter(hidden=true)
            @MemberSession Member member
    ){
        var response = friendBusiness.requestFriend(request, member);

        return Api.OK(response);
    }

    @GetMapping("/friend/accept")
    public Api<FriendResponse> acceptFriend(
            @RequestParam
            Long friendId,

            @Parameter(hidden=true)
            @MemberSession Member member
    ){
        var response = friendBusiness.acceptFriend(friendId,member);

        return Api.OK(response);
    }

    @GetMapping("/friend/reject")
    public Api<FriendResponse> rejectFriend(
            @RequestParam
            Long friendId,

            @Parameter(hidden=true)
            @MemberSession Member member
    ){
        var response = friendBusiness.rejectFriend(friendId,member);

        return Api.OK(response);
    }

    @DeleteMapping("/friend/delete")
    public Api<String> deleteFriend(
            @RequestParam
            Long friendId,

            @Parameter(hidden=true)
            @MemberSession Member member
    ){
        friendBusiness.deleteFriend(friendId,member);

        return Api.OK("친구 삭제 완료");
    }
}
