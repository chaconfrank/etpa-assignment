package com.epta.assigment.application.fraction.command;

import com.epta.assigment.application.mediator.Request;
import com.epta.assigment.domain.Fraction;
import com.epta.assigment.domain.Profile;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddFractionCommand implements Request<Fraction> {
    private String monthCode;
    private Double fraction;
    private Profile profile;
}
