package com.epta.assigment.application.meter.command;

import com.epta.assigment.application.mediator.Request;
import com.epta.assigment.domain.Meter;
import com.epta.assigment.domain.Profile;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddMeterCommand implements Request<Meter> {

    private String meterId;
    private String monthCode;
    private Integer meter;
    private Profile profile;
}
