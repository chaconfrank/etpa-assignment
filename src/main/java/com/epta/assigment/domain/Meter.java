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
@Table(name = "meters", indexes = @Index(columnList = "meter_id, month_code", unique = true))
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

    @Column(name = "month_code", length = 10, nullable = false)
    private String monthCode;

    @Column(name = "meter_reading", nullable = false)
    private Integer meterReading;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;
}
