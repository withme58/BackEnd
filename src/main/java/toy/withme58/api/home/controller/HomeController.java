package toy.withme58.api.home.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.withme58.api.common.api.Api;
import toy.withme58.api.common.token.service.TokenService;
import toy.withme58.api.home.business.HomeBusiness;
import toy.withme58.api.home.dto.response.HomeResponse;
import toy.withme58.api.home.service.HomeService;

@RequiredArgsConstructor
@RestController
public class HomeController {

    private final TokenService tokenService;
    private final HomeBusiness homeBusiness;

    @GetMapping("/")
    public Api<HomeResponse> home(HttpServletRequest request) {
        Long memberId = tokenService.validationToken(request.getHeader("Authorization"));
        HomeResponse homeResponse = homeBusiness.homeResponse(memberId);
        return Api.OK(homeResponse);
    }
}
