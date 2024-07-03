package toy.withme58.api.domain.token.business;

import lombok.RequiredArgsConstructor;
import toy.withMe58.api.common.annotation.Business;
import toy.withMe58.api.common.error.ErrorCode;
import toy.withMe58.api.common.exception.ApiException;
import toy.withMe58.api.domain.token.controller.model.TokenResponse;
import toy.withMe58.api.domain.token.converter.TokenConverter;
import toy.withMe58.api.domain.token.service.TokenService;
import toy.withMe58.db.user.UserEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class TokenBusiness {

    private final TokenConverter tokenConverter;
    private final TokenService tokenService;

    public TokenResponse issueToken(UserEntity userEntity){

        return Optional.ofNullable(userEntity)
                .map(ue->{
                    return ue.getId();
                })
                .map(userId->{
                    var accessToken = tokenService.issueAccessToken(Long.parseLong(userId.toString()));
                    var refreshToken = tokenService.issueRefreshToken(Long.parseLong(userId.toString()));

                    return tokenConverter.toResponse(accessToken,refreshToken);
                })
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }

    public Long validationToken(String accessToken){

        var userId =tokenService.validationToken(accessToken);
        return userId;
    }
}
