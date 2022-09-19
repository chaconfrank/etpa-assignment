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
@Table(name = "meters", indexes = @Index(columnList = "meter_id, profile_id, month_code", unique = true))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Meter {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "meter_id")
    private String meterId;

    @Column(name = "month_code", nullable = false)
    private String monthCode;

    @Column(name = "meter", nullable = false)
    private Integer meter;

    @Column(name = "profile_id", nullable = false)
    private String profileId;
}
