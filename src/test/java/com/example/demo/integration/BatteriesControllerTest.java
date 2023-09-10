package com.example.demo.integration;

import com.example.demo.controller.BatteriesController;
import com.example.demo.entity.PostCodeRangeResponse;
import com.example.demo.services.BatteriesServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(BatteriesController.class)
public class BatteriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Mocking required services and repositories
    @MockBean
    private BatteriesServices service;

    @Before
    public void setUpMocks() {
        PostCodeRangeResponse mockResponse = createMockPostCodeRangeResponse();
        when(service.getBatteriesByPostCodeRange(1, 3)).thenReturn(mockResponse);
    }

    @Test
    public void whenGetBatteries_thenReturnExpectedValues() throws Exception{
        mockMvc.perform(get("/api/v1/batteries/postCodeRange")
                        .param("minPostCode", "1")
                        .param("maxPostCode", "3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.batteryNames[0]").value("Test1"))
                .andExpect(jsonPath("$.batteryNames[1]").value("Test2"))
                .andExpect(jsonPath("$.batteryNames[2]").value("Test3"))
                .andExpect(jsonPath("$.totalWattCapacity").value(123.0))
                .andExpect(jsonPath("$.averageWattCapacity").value(41.0));
    }

    private PostCodeRangeResponse createMockPostCodeRangeResponse() {
        PostCodeRangeResponse response = new PostCodeRangeResponse();
        response.setBatteryNames(Arrays.asList("Test1", "Test2", "Test3"));
        response.setTotalWattCapacity(123.0);
        response.setAverageWattCapacity(41.0);
        return response;
    }
}
