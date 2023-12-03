package com.techelevator.dao;

import com.techelevator.model.Event;

import java.util.List;

public interface EventDao {
    List<Event> getEvent();

    Event getEventById(int id);
}
