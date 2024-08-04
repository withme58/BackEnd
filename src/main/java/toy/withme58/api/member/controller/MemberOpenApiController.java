package toy.withme58.api.member.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.withme58.api.common.api.Api;
import toy.withme58.api.member.business.MemberBusiness;
import toy.withme58.api.member.dto.request.MemberLoginRequest;

import toy.withme58.api.member.dto.request.MemberRegisterRequest;
import toy.withme58.api.member.dto.response.MemberResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberOpenApiController {

    private final MemberBusiness memberBusiness;


    @PostMapping("/signup")
    public Api<MemberResponse> register(
            @Valid
            @RequestBody MemberRegisterRequest memberRegisterRequest

            ) {
        var response = memberBusiness.register(memberRegisterRequest);

        return Api.OK(response);
    }

    @PostMapping("/signin")
    public Api<Object> login(
            @Valid
            @RequestBody MemberLoginRequest memberLoginRequest
    ) {
        var response = memberBusiness.login(memberLoginRequest);
        //Todo 반드시 함수 반환값 다시 점검
        return Api.OK(response);

    }

}
