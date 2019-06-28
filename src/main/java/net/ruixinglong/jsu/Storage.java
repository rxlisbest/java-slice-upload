package net.ruixinglong.jsu;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class Storage {

    protected static String STATUS_SLICE_SUCCESS = "slice_success";
    protected static String STATUS_SLICE_FAILURE = "slice_failure";
    protected static String STATUS_SUCCESS = "success";
    protected static String STATUS_FAILURE = "failure";

    protected String key;
    protected String name;
    protected int chunk = 0;
    protected int chunks = 1;
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

    public String save() {
        String fileName = this.dir + File.separator + this.key;
        String sliceFileName = this.getSliceFileName(fileName, this.chunk);

        try {
            FileOutputStream fos = new FileOutputStream(sliceFileName);

            byte[] b = new byte[1024];
            int length;
            while ((length = this.stream.read(b)) > 0) {
                fos.write(b, 0, length);
            }
            fos.flush();
            fos.close();
        } catch (IOException e) {
            if (this.chunks > 1) {
                return this.STATUS_FAILURE;
            } else {
                return this.STATUS_SLICE_FAILURE;
            }
        }

        Boolean isMerge = true;
        for (int i = 0; i < this.chunks; i++) {
            String sliceFileNameI = this.getSliceFileName(fileName, i);
            File fileI = new File(sliceFileNameI);
            if (!fileI.exists()) {
                isMerge = false;
                break;
            }
        }

        if (isMerge) {
            String lockFileName = this.getLockFileName();
            System.out.println(lockFileName);

            File lockFile = new File(lockFileName);
            try {
                if (!lockFile.exists()) {
                    lockFile.createNewFile();
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(lockFile, "rw");
                FileChannel fileChannel = randomAccessFile.getChannel();
                FileLock fileLock = fileChannel.tryLock();
                if (fileLock != null) {
                    System.out.println(fileName);
                    FileOutputStream fos = new FileOutputStream(fileName);
                    for (int i = 0; i < this.chunks; i++) {
                        String sliceFileNameI = this.getSliceFileName(fileName, i);
                        File fileI = new File(sliceFileNameI);
                        FileInputStream fileInputStreamI = new FileInputStream(fileI);

                        byte[] b = new byte[1024];
                        int length;
                        while ((length = fileInputStreamI.read(b)) > 0) {
                            fos.write(b, 0, length);
                        }
                    }
                    fos.flush();
                    fos.close();
                    fileLock.release();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 遍历检查分片是否全部上传
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
