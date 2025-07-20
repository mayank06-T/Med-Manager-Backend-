package com.mayank.RUST.Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static java.lang.reflect.Array.get;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SaleControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateSale() throws Exception {
        String requestJson = "{\"items\":[{\"productId\":1, \"quantity\":2}]}";

        mockMvc.perform(post("/api/sales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllSales() throws Exception {
        mockMvc.perform(get("/api/sales/"))
                .andExpect(status().isOk());
    }
}
