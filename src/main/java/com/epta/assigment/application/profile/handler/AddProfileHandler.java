package com.epta.assigment.application.profile.handler;

import com.epta.assigment.application.profile.command.AddProfileCommand;
import com.epta.assigment.application.mediator.RequestHandler;
import com.epta.assigment.domain.Profile;
import com.epta.assigment.infraestructure.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AddProfileHandler implements RequestHandler<AddProfileCommand, Profile> {

    private final ProfileRepository repository;

    @Autowired
    public AddProfileHandler(ProfileRepository repository) {
        this.repository = repository;
    }


    @Override
    public Profile handle(AddProfileCommand request) {

        Profile profile = Profile.builder()
                .name(request.getName())
                .build();

        profile = repository.save(profile);

        return profile;
    }
}
