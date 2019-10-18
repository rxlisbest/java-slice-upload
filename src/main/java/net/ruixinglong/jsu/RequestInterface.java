package net.ruixinglong.jsu;

import java.io.InputStream;

public interface RequestInterface {

    String getName();

    String getTempDir();

    InputStream getStream();

    public Object setKey(String key);

    public Object setName();

    public Object setChunk();

    public Object setChunks();

    public Object setTempDir();

    public Object setStream();
}
