package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
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
    private List<String> sunlight;
    @JsonProperty("regular_img_url")
    private String imgUrl;
    @JsonProperty("plant_description")
    private String description;
    @JsonProperty("api_plant_id")
    private int apiPlantId;
    @JsonProperty("sunshine_description")
    private String sunshineDescription;
    @JsonProperty("garden_id")
    private int gardenId;

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

    public List<String> getSunlight() {
        return sunlight;
    }

    public void setSunlight(List<String> sunlight) {
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

    public String getSunshineDescription() {
        return sunshineDescription;
    }

    public void setSunshineDescription(String sunshineDescription) {
        this.sunshineDescription = sunshineDescription;
    }

    public int getGardenId() {
        return gardenId;
    }

    public void setGardenId(int gardenId) {
        this.gardenId = gardenId;
    }

    //Constructor

    public Plant() {
    }

    public Plant(int id, byte[] plantImg, String commonName, String scientificName, String otherName, String watering, List<String> sunlight, String imgUrl, String description, int apiPlantId, String sunshineDescription) {
        this.id = id;
        this.plantImg = plantImg;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.otherName = otherName;
        this.watering = watering;
        this.sunlight = sunlight;
        this.imgUrl = imgUrl;
        this.description = description;
        this.apiPlantId = apiPlantId;
        this.sunshineDescription = sunshineDescription;
    }

    //ToString


    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", plantImg=" + Arrays.toString(plantImg) +
                ", commonName='" + commonName + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", otherName='" + otherName + '\'' +
                ", watering='" + watering + '\'' +
                ", sunlight=" + sunlight +
                ", imgUrl='" + imgUrl + '\'' +
                ", description='" + description + '\'' +
                ", apiPlantId=" + apiPlantId +
                ", sunshineDescription='" + sunshineDescription + '\'' +
                ", gardenId=" + gardenId +
                '}';
    }
}
