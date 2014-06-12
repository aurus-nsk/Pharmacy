package org.pharmacy.web;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:META-INF/spring/applicationContext.xml","file:src/main/webapp/WEB-INF/spring/webmvc-config.xml"})
public class LetterControllerTest {
	
    protected MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext wac;
    
    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    	mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
	
    @Test
    public void testController() throws Exception{
    	MockMultipartFile mockFile = new org.springframework.mock.web.MockMultipartFile("fileData","any.pdf","text/plain", "testdata".getBytes());
    	this.mockMvc.perform(fileUpload("/")
    			.file(mockFile)
    			.locale(Locale.ENGLISH)
                .param("number", "0123456789")
                .param("date", "06/06/2014")
                .param("subject", "egsubject")
                .param("note", "egbignote")
                .param("isPublished", "true"))
                .andExpect(status().isOk());
    }
}