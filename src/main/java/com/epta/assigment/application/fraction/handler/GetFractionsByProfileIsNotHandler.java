package com.epta.assigment.application.fraction.handler;

import com.epta.assigment.application.fraction.query.GetFractionsByProfileQuery;
import com.epta.assigment.application.mediator.RequestHandler;
import com.epta.assigment.infraestructure.repository.FractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetFractionsByProfileIsNotHandler implements RequestHandler<GetFractionsByProfileQuery, Object> {

    private final FractionRepository repository;

    @Autowired
    public GetFractionsByProfileIsNotHandler(FractionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Object handle(GetFractionsByProfileQuery request) {
        return repository.findByProfileIsNot(request.getProfile());
    }
}
