package com.example.demo.controller;

import com.example.demo.entity.Batteries;
import com.example.demo.entity.PostCodeRangeResponse;
import com.example.demo.services.BatteriesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BatteriesController {
    @Autowired
    private BatteriesServices bServices;

    @GetMapping("/batteries")
    public ResponseEntity<List<Batteries>> getAllBatteries(){
        return ResponseEntity.ok().body(this.bServices.getBatteriesList());
    }

    @GetMapping("/batteries/{id}")
    public ResponseEntity<Batteries> getBatteriesById(@PathVariable int id){
        return ResponseEntity.ok().body(this.bServices.getBatteryById(id));
    }

    @GetMapping("/batteries/postCodeRange")
    public ResponseEntity<PostCodeRangeResponse> getBatteriesByPostCodeRange(@RequestParam int minPostCode, @RequestParam int maxPostCode){
        return ResponseEntity.ok().body(this.bServices.getBatteriesByPostCodeRange(minPostCode,maxPostCode));
    }

    @PostMapping("/batteries/addBattery")
    public ResponseEntity<Batteries> addBattery(@RequestBody Batteries battery){
        return ResponseEntity.ok(this.bServices.createBatteries(battery));
    }
    @PostMapping("/batteries/addBatteries")
    public ResponseEntity<List<Batteries>> addBatteries(@RequestBody List<Batteries> bList){
        return ResponseEntity.ok(this.bServices.createBatteriesList(bList));
    }

}