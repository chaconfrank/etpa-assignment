package com.epta.assigment.application.meter.query;

import com.epta.assigment.application.mediator.Request;
import com.epta.assigment.domain.Meter;
import com.epta.assigment.domain.Profile;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetMeterByMeterIdAndProfileQuery implements Request<Object> {
    private Profile profile;
    private String meterId;
}
