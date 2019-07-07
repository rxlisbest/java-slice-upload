package net.ruixinglong.jsu;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.InputStream;

public class WebUploaderRequest {

    protected HttpServletRequest request;
    protected String key;
    protected String name;
    protected int chunk = 0;
    protected int chunks = 1;
    protected String tempDir;
    protected InputStream stream;

    WebUploaderRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        this.request = request;
        this.setKey(null).setName().setChunk().setChunks().setTempDir().setStream();
    }

    public String getKey() {
        return this.key;
    }

    public String getName() {
        return this.name;
    }

    public int getChunk() {
        return this.chunk;
    }

    public int getChunks() {
        return this.chunks;
    }

    public String getTempDir() {
        return this.tempDir;
    }

    public InputStream getStream() {
        return this.stream;
    }

    public WebUploaderRequest setKey(String key) {
        if (key != null) {
            this.key = request.getParameter("key");
        }
        return this;
    }

    public WebUploaderRequest setName() {
        this.name = request.getParameter("name");
        return this;
    }

    public WebUploaderRequest setChunk() {
        if (request.getParameter("chunk") != null) {
            this.chunk = Integer.parseInt(request.getParameter("chunk"));
        }
        return this;
    }

    public WebUploaderRequest setChunks() {
        if (request.getParameter("chunks") != null) {
            this.chunks = Integer.parseInt(request.getParameter("chunks"));
        }
        return this;
    }

    public WebUploaderRequest setTempDir() {
        String tempDir = System.getProperty("java.io.tmpdir");
        this.tempDir = tempDir;
        return this;
    }

    public WebUploaderRequest setStream() {
        try {
            Part file = request.getPart("file");
            this.stream = file.getInputStream();
        } catch (Exception e) {

        }
        return this;
    }
}
