package toy.withme58.api.memberfriend.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import toy.withme58.api.common.annotation.Converter;
import toy.withme58.api.member.dto.Member;
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
}
