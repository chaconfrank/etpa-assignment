package com.epta.assigment.application.profile.handler;

import com.epta.assigment.application.mediator.RequestHandler;
import com.epta.assigment.application.profile.query.GetProfilesQuery;
import com.epta.assigment.infraestructure.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProfilesHandler implements RequestHandler<GetProfilesQuery, Object> {

    private final ProfileRepository respository;

    @Autowired
    public GetProfilesHandler(ProfileRepository respository) {
        this.respository = respository;
    }

    @Override
    public Object handle(GetProfilesQuery request) {
        return respository.findAll();
    }
}
