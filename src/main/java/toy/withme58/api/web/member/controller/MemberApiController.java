package toy.withme58.api.web.member.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.withme58.api.common.annotation.MemberSession;
import toy.withme58.api.common.api.Api;
import toy.withme58.api.domain.member.business.MemberBusiness;
import toy.withme58.api.domain.member.model.MemberResponse;
import toy.withme58.api.domain.member.model.Member;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
//로그인 된 사용자가 들어오는 곳
public class MemberApiController {

    private final MemberBusiness memberBusiness;

    @GetMapping("/me")
    public Api<MemberResponse> me(
            @Parameter(hidden=true)
            @MemberSession Member member
    ){

        var response = memberBusiness.me(member.getId());
        return Api.OK(response);
    }
}
