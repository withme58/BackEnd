package toy.withme58.api.common.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import toy.withme58.api.common.error.ErrorCode;
import toy.withme58.api.common.error.TokenErrorCode;
import toy.withme58.api.common.exception.ApiException;
import toy.withme58.api.common.token.business.TokenBusiness;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBusiness tokenBusiness;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorization Interceptor url : {}", request.getRequestURI());


        //WEB , chrome 의 경우 GET, POST OPTIONS = pass
        if(HttpMethod.OPTIONS.matches(request.getMethod())){
            return true;
        }

        //js, html, png, resource 를 요청하는 경우 =pass
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        String authorizationHeader = request.getHeader("authorization-token");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
        }

        // "Bearer " 부분을 제거하고 실제 토큰만 추출
        String accessToken = authorizationHeader.substring(7);

        // 토큰 검증
        Long memberId = tokenBusiness.validationToken(accessToken);

        if (memberId != null) {
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            requestContext.setAttribute("memberId", memberId, RequestAttributes.SCOPE_REQUEST);
            return true;
        }

        throw new ApiException(ErrorCode.BAD_REQUEST, "인증 실패");

    }
}
