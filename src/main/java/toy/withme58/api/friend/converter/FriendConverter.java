package toy.withme58.api.friend.converter;

import lombok.RequiredArgsConstructor;
import toy.withme58.api.common.annotation.Converter;
import toy.withme58.api.friend.dto.response.FriendResponse;
import toy.withme58.api.friend.dto.response.FriendsResponse;
import toy.withme58.api.member.dto.response.MemberResponse;
import toy.withme58.db.friend.FriendEntity;
import toy.withme58.db.friend.enums.FriendStatus;
import toy.withme58.db.member.MemberEntity;

import java.util.List;

@Converter
@RequiredArgsConstructor
public class FriendConverter {

    public FriendEntity toEntity(
            MemberEntity memberEntity
    ){
        return FriendEntity.builder()
                .email(memberEntity.getEmail())
                .name(memberEntity.getName())
                .build();
    }

    public FriendResponse toResponse(
            FriendEntity friendEntity
    ){
        return FriendResponse.builder()
                .id(friendEntity.getId())
                .email(friendEntity.getEmail())
                .name(friendEntity.getName())
                .status(friendEntity.getStatus())
                .build();
    }

    public FriendsResponse toResponse(
            MemberResponse memberResponse,
            List<FriendResponse> friendResponseList
    ){
        return FriendsResponse.builder()
                .memberResponse(memberResponse)
                .friendResponseList(friendResponseList)
                .build();
    }

    public FriendResponse toResponseByMember(
            MemberEntity memberEntity
    ){
        return FriendResponse.builder()
                .id(memberEntity.getId())
                .email(memberEntity.getEmail())
                .name(memberEntity.getName())
                .status(FriendStatus.REGISTERED)
                .build();
    }

}
