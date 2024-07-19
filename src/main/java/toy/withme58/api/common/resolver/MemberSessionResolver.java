package toy.withme58.api.common.resolver;


import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import toy.withme58.api.common.annotation.MemberSession;
import toy.withme58.api.member.dto.Member;
import toy.withme58.api.member.service.MemberService;

@RequiredArgsConstructor
@Component
public class MemberSessionResolver implements HandlerMethodArgumentResolver {

    private final MemberService memberService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        var annotation =parameter.hasParameterAnnotation(MemberSession.class);

        var parmeterType = parameter.getParameterType().equals(Member.class);

        return (annotation && parmeterType);

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        var requestContext = RequestContextHolder.getRequestAttributes();

        var memberId = requestContext.getAttribute("memberId", RequestAttributes.SCOPE_REQUEST);

        var memberEntity = memberService.getMemberWithThrow(Long.parseLong(memberId.toString()));

        return Member.builder()
                .id(memberEntity.getId())
                .email(memberEntity.getEmail())
                .password(memberEntity.getPassword())
                .name(memberEntity.getName())
                .createdAt(memberEntity.getCreatedAt())
                .status(memberEntity.getStatus())
                .build();
    }
}
