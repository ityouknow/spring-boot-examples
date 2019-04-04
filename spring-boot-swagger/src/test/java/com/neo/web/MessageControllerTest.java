package com.neo.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        saveMessages();
    }

    @Test
    public void saveMessage() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("text", "text");
        params.add("summary", "summary");
        String mvcResult=  mockMvc.perform(MockMvcRequestBuilders.post("/message")
                .params(params)).andReturn().getResponse().getContentAsString();
        System.out.println("Result === "+mvcResult);
    }

    @Test
    public void getAllMessages() throws Exception {
        String mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/messages"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === "+mvcResult);
    }

    @Test
    public void getMessage() throws Exception {
        String mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/message/6"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === "+mvcResult);
    }

    @Test
    public void modifyMessage() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "6");
        params.add("text", "text");
        params.add("summary", "summary");
        String mvcResult= mockMvc.perform(MockMvcRequestBuilders.put("/message").params(params))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === "+mvcResult);
    }

    @Test
    public void patchMessage() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "6");
        params.add("text", "text");
        String mvcResult= mockMvc.perform(MockMvcRequestBuilders.patch("/message/text").params(params))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === "+mvcResult);
    }

    @Test
    public void deleteMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/message/6"))
                .andReturn();
        String mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/messages"))
                .andReturn().getResponse().getContentAsString();
        System.out.println("Result === "+mvcResult);
    }

    private void  saveMessages()  {
        for (int i=1;i<10;i++){
            final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("id",""+i);
            params.add("text", "text"+i);
            params.add("summary", "summary"+i);
            try {
                MvcResult mvcResult=  mockMvc.perform(MockMvcRequestBuilders.post("/message")
                        .params(params)).andReturn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
