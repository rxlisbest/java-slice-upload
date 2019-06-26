package net.ruixinglong.jsu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.stream.Stream;

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
    protected Stream stream;

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

    public Storage setStream(Stream stream) {
        this.stream = stream;
        return this;
    }

    public String save() throws IOException {
        String fileName = this.dir + File.separator + this.key;
        String sliceFileName = this.getSliceFileName(fileName, this.chunk);
        File file = new File(sliceFileName);
        Writer writer = new FileWriter(file);
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
