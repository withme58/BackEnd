package toy.withme58.api.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.withme58.api.common.annotation.MemberSession;
import toy.withme58.api.common.api.Api;
import toy.withme58.api.home.business.HomeBusiness;
import toy.withme58.api.home.dto.response.HomeResponse;
import toy.withme58.api.member.dto.Member;

@RequiredArgsConstructor
@RestController
public class HomeController {

    private final HomeBusiness homeBusiness;

    @GetMapping("/api")
    public Api<HomeResponse> home(@MemberSession Member member) {
        HomeResponse homeResponse = homeBusiness.homeResponse(member.getId());
        return Api.OK(homeResponse);
    }
}
