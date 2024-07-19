package toy.withme58.api.home.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.withme58.api.common.api.Api;

@RestController
public class HomeController {

    @GetMapping("/")
    public Api home() {
        return null;
    }
}
