package com.epta.assigment.application.profile.query;

import com.epta.assigment.application.mediator.Request;
import com.epta.assigment.domain.Profile;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetProfileQuery implements Request<Profile> {
    private String name;
}
