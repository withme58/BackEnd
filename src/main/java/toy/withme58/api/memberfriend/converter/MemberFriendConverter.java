package toy.withme58.api.memberfriend.converter;

import lombok.RequiredArgsConstructor;
import toy.withme58.api.common.annotation.Converter;
import toy.withme58.api.friend.dto.response.FriendResponse;
import toy.withme58.api.member.dto.response.MemberResponse;
import toy.withme58.api.memberfriend.dto.response.MemberFriendResponse;
import toy.withme58.db.friend.FriendEntity;
import toy.withme58.db.member.MemberEntity;
import toy.withme58.db.memberfriend.MemberFriendEntity;

@Converter
@RequiredArgsConstructor
public class MemberFriendConverter {

    public MemberFriendEntity toEntity(
            MemberEntity memberEntity,
            FriendEntity friendEntity
    ){

        MemberFriendEntity memberFriendEntity = new MemberFriendEntity();
        memberFriendEntity.makeMember(memberEntity);
        memberFriendEntity.makeFriend(friendEntity);
        return memberFriendEntity;
    }

    public MemberFriendResponse toResponse(
            MemberResponse memberResponse,
            FriendResponse friendResponse,
            MemberFriendEntity memberFriendEntity
    ){
        return MemberFriendResponse.builder()
                .id(memberFriendEntity.getId())
                .memberResponse(memberResponse)
                .friendResponse(friendResponse)
                .createAt(memberFriendEntity.getCreatedAt())
                .registeredAt(memberFriendEntity.getRegisteredAt())
                .status(memberFriendEntity.getStatus())
                .build();
    }
}
