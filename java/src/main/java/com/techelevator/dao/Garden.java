package com.techelevator.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Garden {
    @JsonProperty("garden_id")
    private int GardenId;

    @JsonProperty("garden_name")
    private String GardenName;

    //Setters and Getters
    public int getGardenId() {
        return GardenId;
    }

    public void setGardenId(int gardenId) {
        GardenId = gardenId;
    }

    public String getGardenName() {
        return GardenName;
    }

    public void setGardenName(String gardenName) {
        GardenName = gardenName;
    }

    //Constructors
    public Garden() {
    }

    public Garden(int gardenId, String gardenName) {
        GardenId = gardenId;
        GardenName = gardenName;
    }

    //ToString
    @Override
    public String toString() {
        return "Garden{" +
                "GardenId=" + GardenId +
                ", GardenName='" + GardenName + '\'' +
                '}';
    }
}
