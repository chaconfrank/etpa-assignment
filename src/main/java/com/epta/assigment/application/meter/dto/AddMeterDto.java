package com.epta.assigment.application.meter.dto;

import com.epta.assigment.application.utils.DateUtils;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddMeterDto {

    @NotBlank
    private String meterId;

    @NotBlank
    @DateTimeFormat( pattern="MMM")
    private String monthCode;

    @NotNull
    private Integer meter;

    @NotBlank
    @Size(min = 1, max = 50)
    private String profile;

    public void setMonthCode(String monthCode) {
        DateUtils.isValidMonth(monthCode);
        this.monthCode = monthCode.toUpperCase();
    }
}
