package com.techelevator.dao;

import com.techelevator.model.Garden;

import java.util.List;

public interface GardenDao {
    List<Garden> getGarden();

    Garden getGardenById(int id);

    Garden createGarden(Garden garden);

}
