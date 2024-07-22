package toy.withme58.api.friend.converter;

import lombok.RequiredArgsConstructor;
import toy.withme58.api.common.annotation.Converter;
import toy.withme58.api.friend.dto.FriendResponse;
import toy.withme58.api.friend.dto.MemberFriendResponse;
import toy.withme58.api.member.dto.response.MemberResponse;
import toy.withme58.db.friend.FriendEntity;
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

    public MemberFriendResponse toResponse(
            MemberResponse memberResponse,
            List<FriendResponse> friendResponseList
    ){
        return MemberFriendResponse.builder()
                .memberResponse(memberResponse)
                .friendResponseList(friendResponseList)
                .build();
    }
}
