package com.epta.assigment.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "fractions", indexes = @Index(columnList = "month_code, profile_id", unique = true))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Fraction {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "fraction_id")
    private String fractionId;

    @Column(name = "month_code", length = 10, nullable = false)
    private String monthCode;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

}