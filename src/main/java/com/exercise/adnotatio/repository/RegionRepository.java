package com.exercise.adnotatio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exercise.adnotatio.model.Region;

public interface RegionRepository extends JpaRepository<Region, Integer>{
  
}
