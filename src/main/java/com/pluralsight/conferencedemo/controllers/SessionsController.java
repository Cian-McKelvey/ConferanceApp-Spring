package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
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
        return sessionRepository.getOne(id);
    }

    @PostMapping // Uses a http post request. No need for request mapping
    // Annotation means that the object returned is changed to json and passed back
    public Session create(@RequestBody final Session session) {
        // saves and stores to database, flush part means it actually gets added to the database, won't work just saving
        return sessionRepository.saveAndFlush(session);
    }

    @DeleteMapping("{id}") // Http delete request
    public void delete(@PathVariable Long id) {
        // Doesn't delete any children records at this time
        sessionRepository.deleteById(id);
    }

    /*
    NOTE: PUT changes all the attributes, PATCH only changes the ones you put in to be changed
    Because this is a put, we need all attributes passed so the data must be validated
    */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT) // Requiring the id on the url, and the HTTP put method
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        Session existingSession = sessionRepository.getOne(id); // Finding the record by id
        // Need to validate this incoming data
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }

}
