package net.ruixinglong.jsu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SliceUploadController {
    @RequestMapping("/slice-upload")
    public String index() {
        return "Hello World";
    }
}
