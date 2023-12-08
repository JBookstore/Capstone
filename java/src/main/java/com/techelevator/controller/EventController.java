package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.model.Event;
import com.techelevator.model.Garden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin
@RestController
public class EventController {
    private final EventDao eventDao;

    public EventController(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/events", method = RequestMethod.GET)
    public List<Event> getAllEvents() {
        return eventDao.getEvent();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/events/{id}", method = RequestMethod.GET)
    public Event getEventById(@PathVariable int id) {
        Event event = eventDao.getEventById(id);
        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "event Not Found");
        } else {
            return eventDao.getEventById(id);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/events", method = RequestMethod.POST)
    public Event create(@RequestBody @Valid Event event) {
        if (event == null) {
            throw new ResponseStatusException(HttpStatus.CREATED, "Event Created");
        } else {
            return eventDao.createEvent(event);
        }
    }
}
