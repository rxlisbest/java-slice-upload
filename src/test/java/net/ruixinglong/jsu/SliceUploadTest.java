package net.ruixinglong.jsu;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.FileInputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class SliceUploadTest {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new SliceUploadController()).build();
    }

    @Test
    public void testSave() throws Exception {
        FileInputStream fis = new FileInputStream("/Users/apple/Pictures/test/php.jpg");
        MockMultipartFile firstFile = new MockMultipartFile("file", "php.jpg", "image/jpeg", fis);
        mvc.perform(MockMvcRequestBuilders.multipart("/slice-upload").file(firstFile).contentType(MediaType.MULTIPART_FORM_DATA).param("name","test.jpg").param("key","test2.jpg"))
            .andExpect(content().string(equalTo("Hello World")));
    }
//
//    @Test
//    public void testSaveQiniu() throws Exception {
//        FileInputStream fis = new FileInputStream("/Users/apple/Pictures/test/php.jpg");
//        MockMultipartFile firstFile = new MockMultipartFile("file", "php.jpg", "image/jpeg", fis);
//        mvc.perform(MockMvcRequestBuilders.post("/slice-upload", firstFile).contentType(MediaType.APPLICATION_OCTET_STREAM).param("name","test.jpg").param("key","test3.jpg"))
//                .andExpect(content().string(equalTo("Hello World")));
//    }
}
