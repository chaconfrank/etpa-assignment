package com.epta.assigment.infraestructure.repository;

import com.epta.assigment.domain.Fraction;
import com.epta.assigment.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FractionRepository extends JpaRepository<Fraction, String> {

    List<Fraction> findByProfileIsNot(Profile profile);

}
