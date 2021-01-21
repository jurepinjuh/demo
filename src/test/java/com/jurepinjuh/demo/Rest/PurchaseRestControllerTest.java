package com.jurepinjuh.demo.Rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jurepinjuh.demo.BrjakpoApplication;
import com.jurepinjuh.demo.Controllers.HttpModels.PurchaseRequest;
import com.jurepinjuh.demo.Models.Article;
import com.jurepinjuh.demo.Models.Item;
import com.jurepinjuh.demo.Models.Purchase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {BrjakpoApplication.class})
@AutoConfigureMockMvc
public class PurchaseRestControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addArticleBadRequest() throws Exception {

        this.mockMvc.perform(
                post("/purchase/add")
                        .with(user("jure").password("1234").authorities(new SimpleGrantedAuthority("USER")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new PurchaseRequest()))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest());

    }

    @Test
    void addArticleUnauthorized() throws Exception {

        this.mockMvc.perform(
                post("/purchase/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new PurchaseRequest()))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isUnauthorized());

    }

    @Test
    void addArticleSucces() throws Exception {
        String TEST_PHONE = "097606021";
        String TEST_NAME = "JURE";
        String TEST_ADDRESS = "ASDA";
        String TEST_EMAIL = "Jurepinjuh@gmail.com";
        double TEST_PRICE = 59.99;
        int TEST_userId = 25;

        int TEST_ARTICLE_ID = 23;
        int TEST_QUAN = 1;
        Purchase purchase = new Purchase();

        purchase.setBillingName(TEST_NAME);
        purchase.setBillingPhone(TEST_PHONE);
        purchase.setBillingAddress(TEST_ADDRESS);
        purchase.setDeliveryName(TEST_NAME);
        purchase.setDeliveryAddress(TEST_ADDRESS);
        purchase.setDeliveryEmail(TEST_EMAIL);
        purchase.setDateOfPurchase(new Date());
        purchase.setUserId(TEST_userId);
        purchase.setPrice(TEST_PRICE);

        Item item = new Item();
        item.setArticleId(TEST_ARTICLE_ID);
        item.setQuantity(TEST_QUAN);
        item.setTotal(TEST_PRICE);
        PurchaseRequest request = new PurchaseRequest();
        request.setPurchase(purchase);
        request.setItemList(new ArrayList<Item>());
        request.getItemList().add(item);
        this.mockMvc.perform(
                post("/purchase/add")
                        .with(user("jure").password("1234").authorities(new SimpleGrantedAuthority("USER")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.billingName").value(TEST_NAME))
                .andExpect(jsonPath("$.billingPhone").value(TEST_PHONE))
                .andExpect(jsonPath("$.billingAddress").value(TEST_ADDRESS))
                .andExpect(jsonPath("$.deliveryName").value(TEST_NAME))
                .andExpect(jsonPath("$.deliveryAddress").value(TEST_ADDRESS))
                .andExpect(jsonPath("$.deliveryEmail").value(TEST_EMAIL))
                .andExpect(jsonPath("$.price").value(TEST_PRICE))
                .andExpect(jsonPath("$.userId").value(TEST_userId))
        ;

    }
}
