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
@Table(name = "profiles", indexes = @Index(columnList = "name", unique = true))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Profile {

    @Id
    @GeneratedValue
    @Column(name = "profile_id")
    private UUID profileId;

    @Column(length = 50, nullable = false)
    private String name;
}
