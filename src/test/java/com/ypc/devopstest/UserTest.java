package com.ypc.devopstest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ypc.devopstest.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ActiveProfiles("test")
public class UserTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
     this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    @After
    public void complete() { }

    @Test
    public void querUser() throws Exception{

        MvcResult authResult = mockMvc.perform(get("/1.0.0/user/get")
                .contentType(MediaType.APPLICATION_XHTML_XML)
                .param("uid", "1")

        ).andExpect(status().isOk()).andReturn();

        String response = authResult.getResponse().getContentAsString();
        Result result = result = objectMapper.readValue(response, Result.class);
        Assert.assertTrue("获取用户数据 失败",result.getCode()==200);
    }

}
