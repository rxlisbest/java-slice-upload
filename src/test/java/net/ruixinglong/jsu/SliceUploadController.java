package net.ruixinglong.jsu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SliceUploadController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
