package cft.shift.manasyan.barter.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SwaggerShower {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
