package toy.withme58.api.domain.user.business;


import lombok.RequiredArgsConstructor;
import toy.withme58.api.common.annotation.Business;
import toy.withme58.api.domain.token.business.TokenBusiness;
import toy.withme58.api.domain.token.controller.model.TokenResponse;
import toy.withme58.api.domain.user.controller.model.UserLoginRequest;
import toy.withme58.api.domain.user.controller.model.UserRegisterRequest;
import toy.withme58.api.domain.user.controller.model.UserResponse;
import toy.withme58.api.domain.user.converter.UserConverter;
import toy.withme58.api.domain.user.service.UserService;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;

    private final TokenBusiness tokenBusiness;


    /*
    * 1. request -> entity  로 변환
    * 2. entity -> service를 호출하여 저장
    * 3. 저장된 entity를 userResponse 로 변환 후 리턴
    * */

    public UserResponse register(UserRegisterRequest userRegisterRequest){

        var entity = userConverter.toUserEntity(userRegisterRequest);

        var userEntity = userService.register(entity);

        var response = userConverter.toUserResponse(userEntity);

        return response;
    }



    public TokenResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(),request.getPassword());

        //토큰 발행

        var tokenResponse = tokenBusiness.issueToken(userEntity);
        return tokenResponse;
    }
}
