package toy.withme58.api.home.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.withme58.api.common.api.Api;
import toy.withme58.api.common.token.service.TokenService;
import toy.withme58.api.home.service.HomeService;

@RequiredArgsConstructor
@RestController
public class HomeController {

    private final TokenService tokenService;
    private final HomeService homeService;

    @GetMapping("/")
    public Api<HomeController> home(HttpServletRequest request) {
        Long memberId = tokenService.validationToken(request.getHeader("Authorization"));
        //home은 질문을 하나 선정해오는게 관건
        //member-question db에서 값이 false인것을 아무거나 가져와야함
        return null;
    }
}
