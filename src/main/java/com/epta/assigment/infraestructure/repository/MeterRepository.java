package com.epta.assigment.infraestructure.repository;

import com.epta.assigment.domain.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MeterRepository extends JpaRepository<Meter, UUID> {
}
