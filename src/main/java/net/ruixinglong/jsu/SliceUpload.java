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
        String tempDir = System.getProperty("java.io.tmpdir");
        return this.upload.setKey("test.jpg").setTempDir(tempDir).setName("test.jpg").setStream(f).save();
    }
}
