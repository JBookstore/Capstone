package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Plant {
    @JsonProperty("plant_id")
    private int id;
    @JsonProperty("plant_img")
    private byte[] plantImg;
    @JsonProperty("common_name")
    private String commonName;
    @JsonProperty("scientific_name")
    private String scientificName;
    @JsonProperty("other_name")
    private String otherName;
    private String watering;
    private String sunlight;
    @JsonProperty("regular_img_url")
    private String imgUrl;
    @JsonProperty("plant_description")
    private String description;
    @JsonProperty("api_plant_id")
    private int apiPlantId;

    //Setters and Getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPlantImg() {
        return plantImg;
    }

    public void setPlantImg(byte[] plantImg) {
        this.plantImg = plantImg;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getWatering() {
        return watering;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }

    public String getSunlight() {
        return sunlight;
    }

    public void setSunlight(String sunlight) {
        this.sunlight = sunlight;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getApiPlantId() {
        return apiPlantId;
    }

    public void setApiPlantId(int apiPlantId) {
        this.apiPlantId = apiPlantId;
    }


    //ToString

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", plantImg='" + plantImg + '\'' +
                ", commonName='" + commonName + '\'' +
                ", scientificName=" + scientificName +
                ", otherName=" + otherName +
                ", watering='" + watering + '\'' +
                ", sunlight=" + sunlight +
                ", imgUrl=" + imgUrl +
                ", description='" + description + '\'' +
                ", apiPlantId=" + apiPlantId +
                '}';
    }
}
