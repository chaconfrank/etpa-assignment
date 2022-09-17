package com.epta.assigment.infraestructure.controller;

import com.epta.assigment.application.profile.command.AddProfileCommand;
import com.epta.assigment.application.profile.dto.AddProfileDto;
import com.epta.assigment.application.mediator.Mediator;
import com.epta.assigment.application.profile.query.GetProfilesQuery;
import com.epta.assigment.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final Mediator mediator;

    @Autowired
    public ProfileController(Mediator mediator) {
        this.mediator = mediator;
    }

    @PostMapping
    public ResponseEntity<Profile> save(@RequestBody @Valid AddProfileDto addProfileDto){

        Profile profile = mediator.send(AddProfileCommand.builder()
                        .name(addProfileDto.getName())
                .build());

        return new ResponseEntity<>(profile, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAll(){

        List<Profile> profiles = (List<Profile>)mediator.send(GetProfilesQuery.builder().build());

        return Optional.of(profiles)
                .filter(c->c.size()!=0)
                .map(items -> new ResponseEntity<>(items, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));


    }


}
