package com.jurepinjuh.demo.Rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jurepinjuh.demo.BrjakpoApplication;
import com.jurepinjuh.demo.Models.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {BrjakpoApplication.class})
@AutoConfigureMockMvc
public class ArticleRestControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getArticleByIDNotFound() throws Exception {
        this.mockMvc.perform(
                get("/article/get/{id}", "-1")
        )
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllArticles() throws Exception {
        this.mockMvc.perform(
                get("/article/getAll")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void addArticleBadRequest() throws Exception {
        this.mockMvc.perform(
                post("/admin/addProduct")
                        .with(user("jure").password("134").roles("ADMIN"))
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    void addArticleSucces() throws Exception {
        String TEST_NAME = "Nikes";
        int TEST_CATEGORIES = 1;
        double TEST_PRICE = 100;
        String TEST_IMAGE = "base64Image";
        String TEST_DESCRIPTION = "DESCRIPTION";
        Article article = new Article();
        article.setName(TEST_NAME);
        article.setPrice(TEST_PRICE);
        article.setGenderId(TEST_CATEGORIES);
        article.setCategoryId(TEST_CATEGORIES);
        article.setBrandId(TEST_CATEGORIES);
        article.setImagePath(TEST_IMAGE);
        article.setDescription(TEST_DESCRIPTION);
        this.mockMvc.perform(
                post("/admin/addProduct")
                        .with(user("jure").password("1234").authorities(new SimpleGrantedAuthority("ADMIN")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(article))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(TEST_NAME))
                .andExpect(jsonPath("$.description").value(TEST_DESCRIPTION))
                .andExpect(jsonPath("$.imagePath").value(TEST_IMAGE))
                .andExpect(jsonPath("$.brandId").value(TEST_CATEGORIES))
                .andExpect(jsonPath("$.categoryId").value(TEST_CATEGORIES))
                .andExpect(jsonPath("$.genderId").value(TEST_CATEGORIES))
                .andExpect(jsonPath("$.price").value(TEST_PRICE));
    }

    @Test
    void addArticleForbidden() throws Exception {
        String TEST_NAME = "Nikes";
        int TEST_CATEGORIES = 1;
        double TEST_PRICE = 100;
        String TEST_IMAGE = "base64Image";
        String TEST_DESCRIPTION = "DESCRIPTION";
        Article article = new Article();
        article.setName(TEST_NAME);
        article.setPrice(TEST_PRICE);
        article.setGenderId(TEST_CATEGORIES);
        article.setCategoryId(TEST_CATEGORIES);
        article.setBrandId(TEST_CATEGORIES);
        article.setImagePath(TEST_IMAGE);
        article.setDescription(TEST_DESCRIPTION);
        this.mockMvc.perform(
                post("/admin/addProduct")
                        .with(user("jure").password("1234").authorities(new SimpleGrantedAuthority("USER")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(article))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void editArticleSucces() throws Exception {
        String TEST_NAME = "Nikes";
        int TEST_ID=23;
        int TEST_CATEGORIES = 1;
        double TEST_PRICE = 100;
        String TEST_IMAGE = "base64Image";
        String TEST_DESCRIPTION = "DESCRIPTION";
        Article article = new Article();
        article.setName(TEST_NAME);
        article.setPrice(TEST_PRICE);
        article.setGenderId(TEST_CATEGORIES);
        article.setCategoryId(TEST_CATEGORIES);
        article.setBrandId(TEST_CATEGORIES);
        article.setImagePath(TEST_IMAGE);
        article.setDescription(TEST_DESCRIPTION);
        article.setId(TEST_ID);
        this.mockMvc.perform(
                put("/admin/editProduct")
                        .with(user("jure").password("1234").authorities(new SimpleGrantedAuthority("ADMIN")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(article))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TEST_ID))
                .andExpect(jsonPath("$.name").value(TEST_NAME))
                .andExpect(jsonPath("$.description").value(TEST_DESCRIPTION))
                .andExpect(jsonPath("$.imagePath").value(TEST_IMAGE))
                .andExpect(jsonPath("$.brandId").value(TEST_CATEGORIES))
                .andExpect(jsonPath("$.categoryId").value(TEST_CATEGORIES))
                .andExpect(jsonPath("$.genderId").value(TEST_CATEGORIES))
                .andExpect(jsonPath("$.price").value(TEST_PRICE));
    }

    @Test
    void editArticleNotFound() throws Exception {
        String TEST_NAME = "Nikes";
        int TEST_ID=-23;
        int TEST_CATEGORIES = 1;
        double TEST_PRICE = 100;
        String TEST_IMAGE = "base64Image";
        String TEST_DESCRIPTION = "DESCRIPTION";
        Article article = new Article();
        article.setName(TEST_NAME);
        article.setPrice(TEST_PRICE);
        article.setGenderId(TEST_CATEGORIES);
        article.setCategoryId(TEST_CATEGORIES);
        article.setBrandId(TEST_CATEGORIES);
        article.setImagePath(TEST_IMAGE);
        article.setDescription(TEST_DESCRIPTION);
        article.setId(TEST_ID);
        this.mockMvc.perform(
                put("/admin/editProduct")
                        .with(user("jure").password("1234").authorities(new SimpleGrantedAuthority("ADMIN")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(article))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteArticleSucces() throws Exception {
        int TEST_ID=23;
        MvcResult result=this.mockMvc.perform(
                delete("/admin/deleteProduct/{id}",TEST_ID)
                        .with(user("jure").password("1234").authorities(new SimpleGrantedAuthority("ADMIN")))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk()).andReturn();
        int deleted= Integer.parseInt(result.getResponse().getContentAsString());
        assertEquals(1,deleted);
    }

}
