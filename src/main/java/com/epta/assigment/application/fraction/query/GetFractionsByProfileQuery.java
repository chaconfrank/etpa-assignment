package com.epta.assigment.application.fraction.query;

import com.epta.assigment.application.mediator.Request;
import com.epta.assigment.domain.Profile;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetFractionsByProfileQuery implements Request<Object> {
    private Profile profile;
}
