package toy.withme58.api.domain.token.converter;

import lombok.RequiredArgsConstructor;
import toy.withMe58.api.common.annotation.Converter;
import toy.withMe58.api.common.error.ErrorCode;
import toy.withMe58.api.common.exception.ApiException;
import toy.withMe58.api.domain.token.controller.model.TokenResponse;
import toy.withMe58.api.domain.token.model.TokenDto;

import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class TokenConverter {

    public TokenResponse toResponse(
            TokenDto accessToken,
            TokenDto refreshToken
    ) {

        Objects.requireNonNull(accessToken,()->{throw new ApiException(ErrorCode.NULL_POINT);});
        Objects.requireNonNull(refreshToken,()->{throw new ApiException(ErrorCode.NULL_POINT);});

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .refreshToken(refreshToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
