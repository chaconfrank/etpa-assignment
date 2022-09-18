package com.epta.assigment.application.profile.handler;

import com.epta.assigment.application.mediator.RequestHandler;
import com.epta.assigment.application.profile.query.GetProfileQuery;
import com.epta.assigment.domain.Profile;
import com.epta.assigment.infraestructure.exception.NoSuchElementFoundException;
import com.epta.assigment.infraestructure.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProfileHandler implements RequestHandler<GetProfileQuery, Profile> {

    private final ProfileRepository repository;

    @Autowired
    public GetProfileHandler(ProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public Profile handle(GetProfileQuery request) {
        return repository.findByName(request.getName())
                .orElseThrow(() ->
                        new NoSuchElementFoundException("Profile Not Found"));
    }
}
