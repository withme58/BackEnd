package toy.withme58.api.home.business;

import lombok.RequiredArgsConstructor;
import toy.withme58.api.common.annotation.Business;
import toy.withme58.api.home.service.HomeService;

@Business
@RequiredArgsConstructor
public class HomeBusiness {

    private final HomeService homeService;
}
