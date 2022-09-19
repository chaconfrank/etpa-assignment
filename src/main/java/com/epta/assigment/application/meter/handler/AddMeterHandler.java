package com.epta.assigment.application.meter.handler;

import com.epta.assigment.application.mediator.RequestHandler;
import com.epta.assigment.application.meter.command.AddMeterCommand;
import com.epta.assigment.application.utils.DateUtils;
import com.epta.assigment.domain.Meter;
import com.epta.assigment.infraestructure.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddMeterHandler implements RequestHandler<AddMeterCommand, Meter> {

    private final MeterRepository repository;

    @Autowired
    public AddMeterHandler(MeterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meter handle(AddMeterCommand request) {
        Meter meter = this.buildMeter(request);
        meter = repository.save(meter);
        return meter;
    }

    private Meter buildMeter(AddMeterCommand request){
        return Meter.builder()
                .meterId(request.getMeterId())
                .meter(request.getMeter())
                .monthCode(request.getMonthCode())
                .profileId(request.getProfile().getProfileId().toString())
                .build();
    }
}
