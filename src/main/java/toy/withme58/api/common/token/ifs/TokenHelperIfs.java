package toy.withme58.api.common.token.ifs;


import toy.withme58.api.common.token.model.TokenDto;

import java.util.Map;

public interface TokenHelperIfs {

    TokenDto issueAccessToken(Map<String, Object> data);

    TokenDto issueRefreshToken(Map<String, Object>data);

    Map<String,Object> validationTokenWithThrow(String token);
}
