package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Plant {

    @JsonProperty("plant_id")
    private int plantId;

    @JsonProperty("plant_name")
    private String plantName;

    //Setters and Getters
    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    //Constructors
    public Plant() {
    }

    public Plant(int plantId, String plantName) {
        this.plantId = plantId;
        this.plantName = plantName;
    }

    //ToString
    @Override
    public String toString() {
        return "Plant{" +
                "plantId=" + plantId +
                ", plantName='" + plantName + '\'' +
                '}';
    }
}
