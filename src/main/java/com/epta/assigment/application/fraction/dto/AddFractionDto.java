package com.epta.assigment.application.fraction.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.Month;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddFractionDto {

    @NotBlank
    @DateTimeFormat( pattern="MMM")
    private Month monthCode;

    @NotBlank
    @DecimalMin(value = "0.00", inclusive = false)
    @DecimalMax(value = "1.00", inclusive = false)
    private Double fraction;

    @NotBlank
    @Size(min = 1, max = 50)
    private String profile;
}
