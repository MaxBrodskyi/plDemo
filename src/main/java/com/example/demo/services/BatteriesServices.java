package com.example.demo.services;

import com.example.demo.entity.Batteries;
import com.example.demo.entity.PostCodeRangeRequest;
import com.example.demo.entity.PostCodeRangeResponse;
import com.example.demo.repository.BatteriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatteriesServices {
    @Autowired
    private BatteriesRepository batteriesRepository;


    public Batteries createBatteries(Batteries battery) {
        return batteriesRepository.save(battery);
    }

    public List<Batteries> createBatteriesList(List<Batteries> batteriesList) {
        return batteriesRepository.saveAll(batteriesList);
    }

    public List<Batteries> getBatteriesList() {
        return batteriesRepository.findAll();
    }

    public Batteries getBatteryById(int batteryId) {
        return batteriesRepository.findById(batteryId).orElse(null);
    }

    public PostCodeRangeResponse getBatteriesByPostCodeRange (int minPostCode, int maxPostCode){
        List<Batteries> filteredBatteries = batteriesRepository.findByPostCodeBetween(minPostCode, maxPostCode);
        filteredBatteries.sort((b1, b2) -> b1.getName().compareToIgnoreCase(b2.getName()));

        double totalWattCapacity = filteredBatteries.stream()
                .mapToDouble(Batteries::getCapacity)
                .sum();

        double averageWattCapacity = filteredBatteries.isEmpty() ? 0 :
                totalWattCapacity / filteredBatteries.size();

        List<String> batteryNames = filteredBatteries.stream()
                .map(Batteries::getName)
                .collect(Collectors.toList());

        PostCodeRangeResponse response = new PostCodeRangeResponse(batteryNames, totalWattCapacity, averageWattCapacity);

        return response;

    }
}
