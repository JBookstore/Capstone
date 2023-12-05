package com.techelevator.controller;

import com.techelevator.dao.EventDao;
import com.techelevator.model.Event;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin
@RestController
public class EventController {
    private final EventDao eventDao;

    public EventController(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(path = "/events", method = RequestMethod.GET)
    public List<Event> getAllEvents() {
        return eventDao.getEvent();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(path = "/events/{id}", method = RequestMethod.GET)
    public Event getEventById(@PathVariable int id) {
        Event event = eventDao.getEventById(id);
        if (event == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "event Not Found");
        } else {
            return eventDao.getEventById(id);
        }
    }
}
