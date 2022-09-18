package com.epta.assigment.infraestructure.controller;

import com.epta.assigment.application.fraction.command.AddFractionCommand;
import com.epta.assigment.application.fraction.dto.AddFractionDto;
import com.epta.assigment.application.fraction.query.GetFractionsByProfileQuery;
import com.epta.assigment.application.mediator.Mediator;
import com.epta.assigment.application.profile.query.GetProfileQuery;
import com.epta.assigment.domain.Fraction;
import com.epta.assigment.domain.Profile;
import com.epta.assigment.infraestructure.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/fractions")
public class FractionController {

    private final Mediator mediator;

    @Autowired
    public FractionController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<Fraction> save(@RequestBody @Valid AddFractionDto addFractionDto){

        Profile profile = mediator.send(GetProfileQuery.builder()
                        .name(addFractionDto.getProfile())
                .build());

        this.isValidFraction(profile, addFractionDto.getFraction());

        Fraction fraction = mediator.send(AddFractionCommand.builder()
                        .fraction(addFractionDto.getFraction())
                        .monthCode(addFractionDto.getMonthCode().toString())
                        .profile(profile)
                .build());

        return new ResponseEntity<>(fraction, HttpStatus.CREATED);

    }

    private void isValidFraction(Profile profile, Double fraction){
        List<Fraction> fractionList = (List<Fraction>)mediator.send(GetFractionsByProfileQuery.builder()
                .profile(profile)
                .build());

        if (fractionList.stream().count() >= 12){
            throw new BadRequestException("You dont add new Fraction to this Profile");
        }

        Double fractions = fractionList
                .stream()
                .mapToDouble(item -> item.getFraction())
                .sum();

        if (fractions + fraction > 1){
            throw new BadRequestException("The value of the fraction is not allowed, it breaks the rule.");
        }

    }
}
