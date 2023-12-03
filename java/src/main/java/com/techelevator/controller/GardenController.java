package com.techelevator.controller;

import com.techelevator.dao.Garden;
import com.techelevator.dao.GardenDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class GardenController {
    private final GardenDao gardenDao;

    public GardenController(GardenDao gardenDao) {
        this.gardenDao = gardenDao;
    }

    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(path = "/gardens", method = RequestMethod.GET)
    public List<Garden> getAllGardens() {
        return gardenDao.getGarden();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @RequestMapping(path = "/gardens/{id}", method = RequestMethod.GET)
    public Garden getGardenById(@PathVariable int id) {
        Garden garden = gardenDao.getGardenById(id);
        if (garden == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "garden Not Found");
        } else {
            return gardenDao.getGardenById(id);
        }
    }
}
