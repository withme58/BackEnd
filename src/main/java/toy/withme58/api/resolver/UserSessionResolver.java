package toy.withme58.api.resolver;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import toy.withMe58.api.common.annotation.UserSession;
import toy.withMe58.api.domain.user.model.User;
import toy.withMe58.api.domain.user.service.UserService;


@RequiredArgsConstructor
@Component
public class UserSessionResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        var annotation =parameter.hasParameterAnnotation(UserSession.class);

        var parmeterType = parameter.getParameterType().equals(User.class);

        return (annotation && parmeterType);

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        var requestContext = RequestContextHolder.getRequestAttributes();

        var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);

        var userEntity = userService.getUserWithThrow(Long.parseLong(userId.toString()));

        return User.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .nickName(userEntity.getNickName())
                .build();
    }
}
