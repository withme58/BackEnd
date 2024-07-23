package toy.withme58.api.home.business;

import lombok.RequiredArgsConstructor;
import toy.withme58.api.common.annotation.Business;
import toy.withme58.api.home.converter.HomeConverter;
import toy.withme58.api.home.dto.response.HomeResponse;
import toy.withme58.api.home.service.HomeService;
import toy.withme58.db.member.MemberEntity;
import toy.withme58.db.member.MemberRepository;

import java.time.LocalDateTime;

@Business
@RequiredArgsConstructor
public class HomeBusiness {

    private final HomeConverter homeConverter;
    private final HomeService homeService;
    private final MemberRepository memberRepository;

    public HomeResponse homeResponse(Long memberId) {
        String question = homeService.findQuestion(memberId);
        MemberEntity member = memberRepository.findById(memberId).get();
        LocalDateTime createdAt = member.getCreatedAt();
        return homeConverter.homeResponse(question, createdAt);
    }
}
