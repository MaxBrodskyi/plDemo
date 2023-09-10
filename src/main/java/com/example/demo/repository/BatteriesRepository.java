package com.example.demo.repository;

import com.example.demo.entity.Batteries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatteriesRepository extends JpaRepository<Batteries, Integer> {
    List<Batteries> findByPostCodeBetween(long minPostCode, long maxPostCode);
}

