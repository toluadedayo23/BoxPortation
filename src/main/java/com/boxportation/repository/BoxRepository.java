package com.boxportation.repository;

import com.boxportation.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoxRepository extends JpaRepository<Box, Long> {

    Optional<Box> findByTxref(String txref);

    boolean existsByTxref(String txref);

    List<Box>  getBoxesByStateContainsAndBatteryCapacityGreaterThanEqual(String state, Double capacity);
}
