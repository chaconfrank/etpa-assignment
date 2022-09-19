package com.epta.assigment.application.fraction.handler;

import com.epta.assigment.application.fraction.command.AddFractionCommand;
import com.epta.assigment.application.mediator.RequestHandler;
import com.epta.assigment.domain.Fraction;
import com.epta.assigment.infraestructure.repository.FractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Month;

@Component
public class AddFractionHandler implements RequestHandler<AddFractionCommand, Fraction> {

    private final FractionRepository repository;

    @Autowired
    public AddFractionHandler(FractionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Fraction handle(AddFractionCommand request) {
        Fraction fraction = this.buildFraction(request);
        fraction = repository.save(fraction);
        return fraction;
    }

    private Fraction buildFraction(AddFractionCommand request){
        return Fraction.builder()
                .fraction(request.getFraction())
                .monthCode(request.getMonthCode())
                .profile(request.getProfile())
                .build();
    }
}
