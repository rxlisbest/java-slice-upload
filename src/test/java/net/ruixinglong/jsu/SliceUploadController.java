package net.ruixinglong.jsu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class SliceUploadController {
    @RequestMapping("/slice-upload")
    public String index(HttpServletRequest request) {
        System.out.println(request.getRequestURI());
        SliceUpload sliceUpload = new SliceUpload("/Users/apple/Pictures/test/");
        sliceUpload.save();
        return "Hello World";
    }
}
