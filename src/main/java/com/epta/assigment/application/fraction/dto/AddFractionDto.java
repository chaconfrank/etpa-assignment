package com.epta.assigment.application.fraction.dto;

import com.epta.assigment.application.utils.DateUtils;
import com.epta.assigment.infraestructure.exception.BadRequestException;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddFractionDto {

    @NotBlank
    @DateTimeFormat( pattern="MMM")
    private String monthCode;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = false)
    @DecimalMax(value = "1.00", inclusive = false)
    private Double fraction;

    @NotBlank
    @Size(min = 1, max = 50)
    private String profile;

    public void setMonthCode(String monthCode) {
        DateUtils.isValidMonth(monthCode);
        this.monthCode = monthCode.toUpperCase();
    }
}
