package com.epta.assigment.application.meter.handler;

import com.epta.assigment.application.mediator.RequestHandler;
import com.epta.assigment.application.meter.query.GetMeterByMeterIdAndProfileQuery;
import com.epta.assigment.domain.Meter;
import com.epta.assigment.infraestructure.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.UUID;

@Component
public class GetMeterByMeterIdAndProfileHandler implements RequestHandler<GetMeterByMeterIdAndProfileQuery
        , Object> {

    private final MeterRepository repository;

    @Autowired
    public GetMeterByMeterIdAndProfileHandler(MeterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Object handle(GetMeterByMeterIdAndProfileQuery request) {
        return repository.findByProfileIdAndMeterId(
                request.getProfile().getProfileId().toString()
                , request.getMeterId());
    }
}
