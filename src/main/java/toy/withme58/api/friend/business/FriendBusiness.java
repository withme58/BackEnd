package toy.withme58.api.friend.business;

import lombok.RequiredArgsConstructor;
import toy.withme58.api.common.annotation.Business;
import toy.withme58.api.friend.converter.FriendConverter;
import toy.withme58.api.friend.dto.MemberFriendResponse;
import toy.withme58.api.friend.service.FriendService;
import toy.withme58.api.member.business.MemberBusiness;
import toy.withme58.api.member.converter.MemberConverter;
import toy.withme58.api.member.dto.Member;
import toy.withme58.api.member.service.MemberService;
import toy.withme58.api.memberfriend.converter.MemberFriendConverter;
import toy.withme58.api.memberfriend.service.MemberFriendService;

import java.util.List;

@Business
@RequiredArgsConstructor
public class FriendBusiness {

    public final MemberService memberService;
    public final MemberConverter memberConverter;

    public final FriendService friendService;
    public final FriendConverter friendConverter;


    public MemberFriendResponse getFriendsByMember(
            Member member
    ) {
        var memberEntity = memberService.getMemberWithThrow(member.getId());


        var memberFriendList = memberEntity.getMemberFriendList();

        var friendResponseList = memberFriendList.stream()
                .map(it -> friendConverter.toResponse(it.getFriend()))
                .toList();

        var memberResponse = memberConverter.toMemberResponse(memberEntity);

        return friendConverter.toResponse(memberResponse,friendResponseList);
    }
}
