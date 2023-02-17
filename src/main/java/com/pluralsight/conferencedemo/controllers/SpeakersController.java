package com.pluralsight.conferencedemo.controllers;

import com.pluralsight.conferencedemo.models.Speaker;
import com.pluralsight.conferencedemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Creating CRUD methods for speakers, see SessionsController for in depth comments
@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> list() {
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping({"id"})
    @ResponseStatus(HttpStatus.FOUND)
    public Speaker get(@PathVariable Long id) {
        return speakerRepository.getOne(id);
    }

    @PostMapping
    public Speaker create(@RequestBody Speaker speaker) {
        return speakerRepository.saveAndFlush(speaker);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        // Implement changes to check for children records while deleting
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker) {
        Speaker existingSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties(existingSpeaker, speaker, "speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }

}
