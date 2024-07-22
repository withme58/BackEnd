package toy.withme58.api.friend.business;

import lombok.RequiredArgsConstructor;
import toy.withme58.api.common.annotation.Business;
import toy.withme58.api.common.error.MemberErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.api.friend.converter.FriendConverter;
import toy.withme58.api.friend.dto.request.FriendAcceptRequest;
import toy.withme58.api.friend.dto.response.FriendResponse;
import toy.withme58.api.friend.dto.response.MemberFriendResponse;
import toy.withme58.api.friend.service.FriendService;
import toy.withme58.api.member.converter.MemberConverter;
import toy.withme58.api.member.dto.Member;
import toy.withme58.api.member.service.MemberService;
import toy.withme58.api.memberfriend.converter.MemberFriendConverter;
import toy.withme58.api.memberfriend.service.MemberFriendService;
import toy.withme58.db.member.MemberEntity;
import toy.withme58.db.member.MemberRepository;
import toy.withme58.db.memberfriend.enums.MemberFriendStatus;

@Business
@RequiredArgsConstructor
public class FriendBusiness {

    public final MemberService memberService;
    public final MemberConverter memberConverter;

    public final FriendService friendService;
    public final FriendConverter friendConverter;

    public final MemberFriendService memberFriendService;
    public final MemberFriendConverter memberFriendConverter;

    public void registerFriend(
            MemberEntity memberEntity
    ){
        var friendEntity = friendConverter.toEntity(memberEntity);

    }

    //Status == REGISTERED 인 경우
    public MemberFriendResponse getFriendsByMember(
            Member member
    ) {
        var memberEntity = memberService.getMember(member.getId());


        var memberFriendList = memberEntity.getMemberFriendList();

        var friendResponseList = memberFriendList.stream()
                .filter(it-> it.getStatus()==MemberFriendStatus.REGISTERED)
                .map(it->{
                    return friendConverter.toResponse(it.getFriend());
                })
                .toList();

        var memberResponse = memberConverter.toMemberResponse(memberEntity);

        return friendConverter.toResponse(memberResponse,friendResponseList);
    }

    public FriendResponse acceptFriendByFriendName(FriendAcceptRequest request , Member member) {

        var memberEntity = memberService.getMember(member.getId());

        validateAcceptFriend(request.getName(),memberEntity);

        var friendEntity = friendService.searchOne(request.getName());

        var memberFriendEntity = memberFriendService.searchOneWaiting(member.getId(),friendEntity.getId());

        memberFriendService.statusRegistered(memberFriendEntity);

        return friendConverter.toResponse(friendEntity);
    }

    private void validateAcceptFriend(String friendName, MemberEntity memberEntity){


        if(friendName.equals(memberEntity.getName())){
            throw new ApiException(MemberErrorCode.Member_Friend_Duplicate);
        }

        memberEntity.getMemberFriendList().stream()
                .filter(it -> it.getStatus() == MemberFriendStatus.REGISTERED)
                .forEach(it -> {
                    if (it.getFriend().getName().equals(friendName)) {
                        throw new ApiException(MemberErrorCode.Member_Friend_Duplicate);
                    }
                });
    }
}
