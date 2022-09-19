package com.epta.assigment.infraestructure.controller;

import com.epta.assigment.application.mediator.Mediator;
import com.epta.assigment.application.meter.command.AddMeterCommand;
import com.epta.assigment.application.meter.dto.AddMeterDto;
import com.epta.assigment.application.meter.query.GetMeterByMeterIdAndProfileQuery;
import com.epta.assigment.application.profile.query.GetProfileQuery;
import com.epta.assigment.application.utils.DateUtils;
import com.epta.assigment.domain.Meter;
import com.epta.assigment.domain.Profile;
import com.epta.assigment.infraestructure.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/api/meters")
public class MeterController {

    private final Mediator mediator;

    @Autowired
    public MeterController(Mediator mediator) {
        this.mediator = mediator;
    }


    @PostMapping
    public ResponseEntity<Meter> save(@RequestBody @Valid AddMeterDto addMeterDto) {

        Profile profile = mediator.send(GetProfileQuery.builder()
                .name(addMeterDto.getProfile())
                .build());

        List<Meter> meters = (List<Meter>)mediator.send(GetMeterByMeterIdAndProfileQuery.builder()
                .meterId(addMeterDto.getMeterId())
                .profile(profile)
                .build());

        if (!meters.isEmpty()){
            this.isNotDiplicated(meters, addMeterDto);
            this.isValidMeter(meters, addMeterDto);
        }

        Meter meter = mediator.send(AddMeterCommand.builder()
                        .meterId(addMeterDto.getMeterId())
                        .meter(addMeterDto.getMeter())
                        .profile(profile)
                        .monthCode(addMeterDto.getMonthCode())
                .build());

        return new ResponseEntity<>(meter, HttpStatus.CREATED);
    }

    private void isValidMeter(List<Meter> meters, AddMeterDto addMeterDto){

        Integer actualMonth = DateUtils.getMonth(addMeterDto.getMonthCode()).getValue();

        for (int i = actualMonth - 1; i > 0; i--){
            int finalI = i;
            String finalMonth = Month.of(finalI).getDisplayName(TextStyle.SHORT, Locale.US).toUpperCase();

            Optional<Meter> tempM = Optional.ofNullable(meters
                    .stream()
                    .filter(item -> finalMonth.equals(item.getMonthCode()))
                    .findAny()
                    .orElse(null));

            if (!tempM.isEmpty() && tempM.get().getMeter() >= addMeterDto.getMeter()){
                throw new BadRequestException("The meter reading is wrong");
            }
        }

        for (int i = actualMonth + 1; i <= 12; i++){
            int finalI = i;
            String finalMonth = Month.of(finalI).getDisplayName(TextStyle.SHORT, Locale.US).toUpperCase();

            Optional<Meter> tempM = Optional.ofNullable(meters
                    .stream()
                    .filter(item -> finalMonth.equals(item.getMonthCode()))
                    .findAny()
                    .orElse(null));

            if (!tempM.isEmpty() && tempM.get().getMeter() <= addMeterDto.getMeter()){
                throw new BadRequestException("The meter reading is wrong");
            }
        }


    }

    private void isNotDiplicated(List<Meter> meters, AddMeterDto addMeterDto) {

        Optional<Meter> tempMeter = meters
                .stream()
                .filter(item -> addMeterDto.getMonthCode().equals(item.getMonthCode()))
                .findAny();

        if (!tempMeter.isEmpty()){
            throw new BadRequestException("The month is wrong");
        }
    }
}
