package toy.withme58.api.home.converter;

import lombok.RequiredArgsConstructor;
import toy.withme58.api.common.annotation.Converter;
import toy.withme58.api.home.dto.response.FriendResponse;
import toy.withme58.api.home.dto.response.HomeResponse;
import toy.withme58.api.home.dto.response.MemberFriendDto;
import toy.withme58.db.member.enums.MemberStatus;
import toy.withme58.db.memberfriend.enums.MemberFriendStatus;

import java.time.LocalDateTime;
import java.util.List;

@Converter
public class HomeConverter {

    public HomeResponse homeResponse(String question, LocalDateTime createdAt) {
        return HomeResponse.builder()
                .question(question)
                .createdAt(createdAt)
                .status(MemberStatus.REGISTERED.getStatus()).build();
    }

    public FriendResponse friendResponse(List<MemberFriendDto> dto) {
        return FriendResponse.builder()
                .friends(dto)
                .status(MemberFriendStatus.REGISTERED.getStatus()).build();
    }
}
