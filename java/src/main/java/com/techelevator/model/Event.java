package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {
    @JsonProperty("event_id")
    private int eventId;
    @JsonProperty("garden_id")
    private int gardenId;
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("event_description")
    private String eventDescription;
    @JsonProperty("event_coordinator")
    private String eventCoordinator;
    @JsonProperty("user_volunteer")
    private String userVolunteer;
    @JsonProperty("childcare_owner")
    private String childcareOwner;
    @JsonProperty("event_date")
    private String eventDate;
    @JsonProperty("event_category")
    private String eventCategory;

    //Setters and Getters

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getGardenId() {
        return gardenId;
    }

    public void setGardenId(int gardenId) {
        this.gardenId = gardenId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventCoordinator() {
        return eventCoordinator;
    }

    public void setEventCoordinator(String eventCoordinator) {
        this.eventCoordinator = eventCoordinator;
    }

    public String getUserVolunteer() {
        return userVolunteer;
    }

    public void setUserVolunteer(String userVolunteer) {
        this.userVolunteer = userVolunteer;
    }

    public String getChildcareOwner() {
        return childcareOwner;
    }

    public void setChildcareOwner(String childcareOwner) {
        this.childcareOwner = childcareOwner;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", gardenId=" + gardenId +
                ", userId=" + userId +
                ", eventDescription='" + eventDescription + '\'' +
                ", eventCoordinator='" + eventCoordinator + '\'' +
                ", userVolunteer='" + userVolunteer + '\'' +
                ", childcareOwner='" + childcareOwner + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", eventCategory='" + eventCategory + '\'' +
                '}';
    }
}
