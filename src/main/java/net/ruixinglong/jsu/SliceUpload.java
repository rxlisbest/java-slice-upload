package net.ruixinglong.jsu;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SliceUpload {

    protected Storage upload;

    SliceUpload(String directory) {
        this.upload = new Storage(directory);
    }

    public String save() throws IOException {
        InputStream f = new FileInputStream("/Users/apple/Pictures/test/php.jpg");
        return this.upload.setKey("test.jpg").setName("test.jpg").setStream(f).save();
    }
}
