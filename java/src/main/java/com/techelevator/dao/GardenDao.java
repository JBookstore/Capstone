package com.techelevator.dao;

import java.util.List;

public interface GardenDao {
    List<Garden> getGarden();

    Garden getGardenById(int id);
}
