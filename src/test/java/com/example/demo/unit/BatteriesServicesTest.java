package com.example.demo.unit;

import static org.assertj.core.api.Assertions.assertThat;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class) // This is for JUnit 4. For JUnit 5, you won't need this.
@SpringBootTest
public class BatteriesServicesTest { // Replace 'YourServiceClass' with the actual name of the service class.

    @Autowired
    private BatteriesServices service;

    @MockBean
    private BatteriesRepository batteriesRepository;

    @Before
    public void setUp() {
        Batteries battery1 = new Batteries();
        battery1.setId(1);
        battery1.setName("Test1");
        battery1.setPostCode(1L);
        battery1.setCapacity(1);
        battery1.setCapacityType("WATT");

        Batteries battery2 = new Batteries();
        battery2.setId(2);
        battery2.setName("Test2");
        battery2.setPostCode(2L);
        battery2.setCapacity(2);
        battery2.setCapacityType("WATT");

        Batteries battery3 = new Batteries();
        battery3.setId(3);
        battery3.setName("Test3");
        battery3.setPostCode(3L);
        battery3.setCapacity(120);
        battery3.setCapacityType("WATT");

        List<Batteries> mockData = Arrays.asList(battery1, battery2, battery3);

        when(batteriesRepository.findByPostCodeBetween(1, 3)).thenReturn(mockData);
    }

    @Test
    public void testMockBehavior() {
        List<Batteries> batteries = batteriesRepository.findByPostCodeBetween(1, 3);
        assertEquals(3, batteries.size());
    }

    @Test
    public void getBatteriesByPostCodeRangeTest() {
        int minPostCode = 1;
        int maxPostCode = 3;
        PostCodeRangeResponse response = service.getBatteriesByPostCodeRange(minPostCode, maxPostCode);

        assertThat(response.getBatteryNames()).containsExactly("Test1", "Test2", "Test3");
        assertThat(response.getTotalWattCapacity()).isEqualTo(123.0);
        assertThat(response.getAverageWattCapacity()).isEqualTo(41.0);
    }

}
