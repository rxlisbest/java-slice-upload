package net.ruixinglong.jsu;

import java.io.*;

public class Storage {

    protected static String STATUS_SLICE_SUCCESS = "slice_success";
    protected static String STATUS_SLICE_FAILURE = "slice_failure";
    protected static String STATUS_SUCCESS = "success";
    protected static String STATUS_FAILURE = "failure";

    protected String key;
    protected String name;
    protected int chunk = 0;
    protected int chunks = 0;
    protected String dir;
    protected String tempDir;
    protected InputStream stream;

    Storage(String directory) {
        this.dir = directory;
    }

    public Storage setChunk(int chunk) {
        this.chunk = chunk;
        return this;
    }

    public Storage setChunks(int chunks) {
        this.chunks = chunks;
        return this;
    }

    public Storage setKey(String key) {
        this.key = key;
        return this;
    }

    public Storage setName(String name) {
        this.name = name;
        return this;
    }

    public Storage setTempDir(String tempDir) {
        this.tempDir = tempDir;
        return this;
    }

    public Storage setStream(InputStream stream) {
        this.stream = stream;
        return this;
    }

    public String save() throws IOException {
        String fileName = this.dir + File.separator + this.key;
        String sliceFileName = this.getSliceFileName(fileName, this.chunk);
        FileOutputStream fos = new FileOutputStream(sliceFileName);

        byte[] b = new byte[1024];
        int length;
        while ((length = this.stream.read(b)) > 0) {
            fos.write(b, 0, length);
        }

        fos.close();
//        writer.write(this.stream);
        return "";
    }

    protected String getLockFileName() {
        return String.format("%s/%s.lock", this.tempDir, this.key);
    }

    protected String getSliceFileName(String fileName, int chunk) {
        return String.format("%s_%d", fileName, chunk);
    }
}
