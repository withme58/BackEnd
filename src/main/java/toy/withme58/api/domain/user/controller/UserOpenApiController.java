package toy.withme58.api.domain.user.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.withMe58.api.common.api.Api;
import toy.withMe58.api.domain.user.business.UserBusiness;
import toy.withMe58.api.domain.user.controller.model.UserLoginRequest;
import toy.withMe58.api.domain.user.controller.model.UserRegisterRequest;
import toy.withMe58.api.domain.user.controller.model.UserResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
public class UserOpenApiController {

    private final UserBusiness userBusiness;


    @PostMapping("/register")
    public Api<UserResponse> register(
            @Valid
            @RequestBody UserRegisterRequest userRegisterRequest

    ) {
        var response = userBusiness.register(userRegisterRequest);

        return Api.OK(response);
    }

    @PostMapping("/login")
    public Api<Object> login(
            @Valid
            @RequestBody UserLoginRequest userLoginRequest
    ) {
        var response = userBusiness.login(userLoginRequest);
        //Todo 반드시 함수 반환값 다시 점검
        return Api.OK(response);

    }

}
