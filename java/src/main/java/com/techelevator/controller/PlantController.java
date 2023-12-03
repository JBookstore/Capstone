package com.techelevator.controller;

import com.techelevator.dao.PlantDao;
import com.techelevator.model.Plant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PlantController {
    private final PlantDao plantDao;

    public PlantController(PlantDao plantDao) {
        this.plantDao = plantDao;
    }

    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(path = "/plants", method = RequestMethod.GET)
    public List<Plant> getAllPlants() {
        return plantDao.getPlants();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(path = "/plants/{id}", method = RequestMethod.GET)
    public Plant getPlantById(@PathVariable int id) {
        Plant plant = plantDao.getPlantById(id);
        if (plant == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "plant Not Found");
        } else {
            return plantDao.getPlantById(id);
        }
    }
}


