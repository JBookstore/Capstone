package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {
    @JsonProperty("event_id")
    private int EventId;

    @JsonProperty("event_name")
    private String EventName;

    //Setters and Getters

    public int getEventId() {
        return EventId;
    }

    public void setEventId(int eventId) {
        EventId = eventId;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }


    //Constructors

    public Event() {
    }

    public Event(int eventId, String eventName) {
        EventId = eventId;
        EventName = eventName;
    }

    //ToString

    @Override
    public String toString() {
        return "Event{" +
                "EventId=" + EventId +
                ", EventName='" + EventName + '\'' +
                '}';
    }
}
