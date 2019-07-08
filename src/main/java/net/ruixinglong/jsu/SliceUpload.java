package net.ruixinglong.jsu;

public class SliceUpload {

    protected Storage upload;

    SliceUpload(String directory) {
        this.upload = new Storage(directory);
    }

    public String save() {
        try {
            WebUploaderRequest webUploaderRequest = new WebUploaderRequest();
            System.out.println("kEY:" + webUploaderRequest.getKey());
            return this.upload.setKey(webUploaderRequest.getKey()).setTempDir(webUploaderRequest.getTempDir()).setName(webUploaderRequest.getName()).setStream(webUploaderRequest.getStream()).save();
        } catch (Exception e) {
            return "";
        }
    }
}
