package net.ruixinglong.jsu;

public class SliceUpload {

    protected Storage upload;

    SliceUpload(String directory) {
        upload = new Storage(directory);
    }

    public String save() {
        try {
            WebUploaderRequest webUploaderRequest = new WebUploaderRequest();
            RequestFactory requestFactory = new RequestFactory();
            RequestInterface request = (RequestInterface) requestFactory.getRequest();
            return upload.setKey(webUploaderRequest.getKey()).setTempDir(request.getTempDir()).setName(request.getName()).setStream(request.getStream()).save();
        } catch (Exception e) {
            return "";
        }
    }
}
