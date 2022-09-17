package com.epta.assigment.application.profile.command;

import com.epta.assigment.application.mediator.Request;
import com.epta.assigment.domain.Profile;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddProfileCommand implements Request<Profile> {
    private String name;
}
