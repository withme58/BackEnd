package toy.withme58.api.home.converter;

import lombok.RequiredArgsConstructor;
import toy.withme58.api.common.annotation.Converter;
import toy.withme58.api.home.dto.response.HomeResponse;
import toy.withme58.db.member.enums.MemberStatus;

import java.time.LocalDateTime;

@Converter
public class HomeConverter {

    public HomeResponse homeResponse(String question,LocalDateTime createdAt) {
        return HomeResponse.builder()
                .question(question)
                .createdAt(createdAt)
                .status(MemberStatus.REGISTERED.getStatus()).build();
    }
}
