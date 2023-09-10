package com.example.demo.unit;

import com.example.demo.entity.Batteries;
import com.example.demo.entity.PostCodeRangeResponse;
import com.example.demo.repository.BatteriesRepository;
import com.example.demo.services.BatteriesServices;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatteriesServicesTest {

    @Autowired
    private BatteriesServices service;

    @MockBean
    private BatteriesRepository batteriesRepository;

    @Before
    public void setUp() {
        List<Batteries> mockData = Arrays.asList(
                createBattery(1, "Test1", 1L, 1, "WATT"),
                createBattery(2, "Test2", 2L, 2, "WATT"),
                createBattery(3, "Test3", 3L, 120, "WATT")
        );

        when(batteriesRepository.findByPostCodeBetween(1, 3)).thenReturn(mockData);
    }

    @Test
    public void testMockBehavior() {
        List<Batteries> batteries = batteriesRepository.findByPostCodeBetween(1, 3);
        assertEquals(3, batteries.size());
    }

    @Test
    public void getBatteriesByPostCodeRangeTest() {
        PostCodeRangeResponse response = service.getBatteriesByPostCodeRange(1, 3);

        assertThat(response.getBatteryNames()).containsExactly("Test1", "Test2", "Test3");
        assertThat(response.getTotalWattCapacity()).isEqualTo(123.0);
        assertThat(response.getAverageWattCapacity()).isEqualTo(41.0);
    }

    private Batteries createBattery(int id, String name, long postCode, int capacity, String capacityType) {
        Batteries battery = new Batteries();
        battery.setId(id);
        battery.setName(name);
        battery.setPostCode(postCode);
        battery.setCapacity(capacity);
        battery.setCapacityType(capacityType);
        return battery;
    }
}
