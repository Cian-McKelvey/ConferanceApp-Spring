package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Adding rest api controller for sessions
@RestController // Makes it respond to incoming/outgoing payloads as json rest endpoints
@RequestMapping("/api/v1/sessions") // Tells the router what the mapping url will be
public class SessionsController {

    @Autowired // Creates an instance of SessionRepository and adds it to the class
    private SessionRepository sessionRepository;

    // Creating a list endpoint that will return all the sessions when called
    @GetMapping // Defines as a HTTP get request
    public List<Session> list() {
        return sessionRepository.findAll();
    }

    // Ability to get item by id (returns a reference)
    @GetMapping // Defines as a HTTP get request
    @RequestMapping("{id}") // Adds id to the class RequestMapping url, gets specific objects (/api/v1/sessions/{id})
    @ResponseStatus(HttpStatus.FOUND) // Added to return the correct Http Response, returns 200's otherwise
    public Session get(@PathVariable Long id) {
        // Tutorial uses getOne method, but it is depreciated, this method returns a reference, not the actual object
        return sessionRepository.getReferenceById(id);
    }

}
