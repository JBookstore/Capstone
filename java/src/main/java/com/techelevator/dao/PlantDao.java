package com.techelevator.dao;

import com.techelevator.model.Plant;

import java.util.List;

public interface PlantDao {

        List<Plant> getPlants();

        Plant getPlantById(int id);
}
