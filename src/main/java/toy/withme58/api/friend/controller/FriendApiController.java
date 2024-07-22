package toy.withme58.api.friend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.withme58.api.common.annotation.MemberSession;
import toy.withme58.api.common.api.Api;
import toy.withme58.api.friend.business.FriendBusiness;
import toy.withme58.api.friend.dto.MemberFriendResponse;
import toy.withme58.api.member.dto.Member;
import toy.withme58.db.friend.FriendEntity;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class FriendApiController {

    private final FriendBusiness friendBusiness;

    @GetMapping("friends")
    public Api<MemberFriendResponse> showFriendList(
            @MemberSession Member member
    ) {
        var response = friendBusiness.getFriendsByMember(member);
        return Api.OK(response);
    }


}
