package com.techelevator.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true) // This annotation tells Jackson to ignore fields not defined in the class
public class PlantDescriptionResponse {
    private String description;
    // Getters and Setters
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}