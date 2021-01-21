package com.jurepinjuh.demo.Rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.jurepinjuh.demo.Authenticatiion.JwtUtils;
import com.jurepinjuh.demo.BrjakpoApplication;
import com.jurepinjuh.demo.Controllers.HttpModels.LoginRequest;
import com.jurepinjuh.demo.Controllers.HttpModels.RegisterRequest;
import com.jurepinjuh.demo.Models.User;
import com.jurepinjuh.demo.Repository.JpaUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {BrjakpoApplication.class})
@AutoConfigureMockMvc
public class AuthRestControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void registerSucces() throws Exception {
        String TEST_USERNAME="jure1234";
        String TEST_EMAIL="jurepinjuh12345@gmail.com";
        int TEST_ROLEID=2;
        String TEST_PASSWORD="12345678";
        RegisterRequest registerRequest=new RegisterRequest();
        registerRequest.setUsername(TEST_USERNAME);
        registerRequest.setEmail(TEST_EMAIL);
        registerRequest.setPassword(TEST_PASSWORD);
        MvcResult result=this.mockMvc.perform(
                post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value(TEST_USERNAME))
                .andExpect(jsonPath("$.email").value(TEST_EMAIL))
                .andExpect(jsonPath("$.role.id").value(TEST_ROLEID))
                .andReturn();
        String encodedPassword= JsonPath.read(result.getResponse().getContentAsString(),"$.password");
        assertTrue(encoder.matches(TEST_PASSWORD,encodedPassword));
    }

    @Test
    void register409() throws Exception {
        String TEST_USERNAME="jure";
        String TEST_EMAIL="jurepinjuh12345@gmail.com";
        String TEST_PASSWORD="12345678";
        RegisterRequest registerRequest=new RegisterRequest();
        registerRequest.setUsername(TEST_USERNAME);
        registerRequest.setEmail(TEST_EMAIL);
        registerRequest.setPassword(TEST_PASSWORD);
        this.mockMvc.perform(
                post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isConflict());
    }

    @Test
    void loginSucces() throws Exception{
        String TEST_USERNAME="jure";
        String TEST_PASSWORD="1234";
        LoginRequest request=new LoginRequest();
        request.setUsername(TEST_USERNAME);
        request.setPassword(TEST_PASSWORD);
        MvcResult result=this.mockMvc.perform(
                post("/auth/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request))
                    .accept(MediaType.TEXT_PLAIN)
        ).andExpect(status().isOk()).andReturn();

        String jwt=result.getResponse().getContentAsString();
        assertTrue(jwtUtils.validateJwtToken(jwt));
    }

    @Test
    void loginUnauthorized() throws Exception{
        String TEST_USERNAME="jure";
        String TEST_PASSWORD="12345";
        LoginRequest request=new LoginRequest();
        request.setUsername(TEST_USERNAME);
        request.setPassword(TEST_PASSWORD);
        this.mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isUnauthorized());
    }
}
