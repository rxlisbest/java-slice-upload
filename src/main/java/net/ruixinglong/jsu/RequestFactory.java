package net.ruixinglong.jsu;

import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class RequestFactory {

    private HttpServletRequest request;

    RequestFactory() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
    }

    public Object getRequest() throws Exception {
        if (request.getContentType().equals(MediaType.MULTIPART_FORM_DATA)) {
            return new WebUploaderRequest();
        } else if (request.getContentType().equals(MediaType.APPLICATION_OCTET_STREAM)) {
            return new QiniuRequest();
        } else {
            throw new Exception();
        }
    }
}
