package com.boxportation.repository;

import com.boxportation.model.Box;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoxRepository extends JpaRepository<Box, Long> {

    Optional<Box> findByTxref(String txref);

    boolean existsByTxref(String txref);

    @Query(value = "select * from BOX where state = 'IDLE' and BATTERY_CAPACITY  >= 25.0;", nativeQuery = true)
    List<Box>  getBoxesByBatteryCapacityGreaterThanEqualAndStateEquals();
}
