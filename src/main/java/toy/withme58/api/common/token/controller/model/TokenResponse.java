package toy.withme58.api.common.token.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponse {

    private String accessToken;
    private String refreshToken;

    private LocalDateTime accessTokenExpiredAt;
    private LocalDateTime refreshTokenExpiredAt;
}
