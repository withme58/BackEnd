package toy.withme58.api.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.withme58.api.common.annotation.UserSession;
import toy.withme58.api.common.api.Api;
import toy.withme58.api.domain.user.business.UserBusiness;
import toy.withme58.api.domain.user.controller.model.UserResponse;
import toy.withme58.api.domain.user.model.User;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
//로그인 된 사용자가 들어오는 곳
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me(
            @UserSession User user
    ){

        var response = userBusiness.me(user.getId());
        return Api.OK(response);
    }
}
