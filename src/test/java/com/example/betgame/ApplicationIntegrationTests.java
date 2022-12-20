package com.example.betgame;

import com.example.betgame.dto.RequestDto;
import com.example.betgame.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
* Wrote only two test cases for demo purpose and these test cases will not cover all scenarios
* */
@SpringBootTest(classes = BetGameApplication.class)
public class ApplicationIntegrationTests extends AbstractTestNGSpringContextTests{

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testPlaceBet_success() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(new RequestDto(50,40.5));

        MvcResult result = mockMvc.perform(get("/v1/bet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        ResponseDto response = objectMapper.readValue(responseJson, ResponseDto.class);

        if(response.isWin()){
            assertThat(new BigDecimal("80.19").round(new MathContext(4, RoundingMode.HALF_UP))).isEqualTo(response.getWinningAmount());
        } else {
            assertThat(new BigDecimal("0.0")).isEqualTo(response.getWinningAmount());
        }

    }

    @Test
    public void testPlaceBet_invalid_input() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        String request = objectMapper.writeValueAsString(new RequestDto(500,40.5));

        mockMvc.perform(get("/v1/bet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("{\"number\":\"Number must be less than 100\"}"));

    }
}