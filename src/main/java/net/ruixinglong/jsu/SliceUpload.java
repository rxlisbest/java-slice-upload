package net.ruixinglong.jsu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.InputStream;

public class SliceUpload {

    protected Storage upload;

    SliceUpload(String directory) {
        this.upload = new Storage(directory);
    }

    public String save() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes()).getRequest();
            System.out.println(request);
            InputStream f = new FileInputStream("/Users/apple/Pictures/test/php.jpg");
            String tempDir = System.getProperty("java.io.tmpdir");
            return this.upload.setKey("test.jpg").setTempDir(tempDir).setName("test.jpg").setStream(f).save();
        } catch (Exception e) {
            return "";
        }
    }
}
