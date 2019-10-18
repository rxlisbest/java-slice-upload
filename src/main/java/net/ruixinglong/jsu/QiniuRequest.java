package net.ruixinglong.jsu;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;

public class QiniuRequest implements RequestInterface {

    protected HttpServletRequest request;

    protected String key;

    protected String name;

    protected int chunk = 0;

    protected int chunks = 1;

    protected String tempDir;

    protected InputStream stream;

    QiniuRequest() {
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

    public QiniuRequest setKey(String key) {
        if (key == null) {
            this.key = request.getParameter("key");
        }
        return this;
    }

    public QiniuRequest setName() {
        this.name = request.getParameter("name");
        return this;
    }

    public QiniuRequest setChunk() {
        if (request.getParameter("chunk") != null) {
            this.chunk = Integer.parseInt(request.getParameter("chunk"));
        }
        return this;
    }

    public QiniuRequest setChunks() {
        if (request.getParameter("chunks") != null) {
            this.chunks = Integer.parseInt(request.getParameter("chunks"));
        }
        return this;
    }

    public QiniuRequest setTempDir() {
        String tempDir = System.getProperty("java.io.tmpdir");
        this.tempDir = tempDir;
        return this;
    }

    public QiniuRequest setStream() {
        try {
            this.stream = request.getInputStream();
        } catch (Exception e) {

        }
        return this;
    }
}
