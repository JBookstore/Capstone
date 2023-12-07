package com.techelevator.controller;

import com.techelevator.model.Garden;
import com.techelevator.dao.GardenDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin
@RestController
public class GardenController {
    private final GardenDao gardenDao;

    public GardenController(GardenDao gardenDao) {
        this.gardenDao = gardenDao;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/gardens", method = RequestMethod.GET)
    public List<Garden> getAllGardens() {
        return gardenDao.getGarden();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/gardens/{id}", method = RequestMethod.GET)
    public Garden getGardenById(@PathVariable int id) {
        Garden garden = gardenDao.getGardenById(id);
        if (garden == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "garden Not Found");
        } else {
            return gardenDao.getGardenById(id);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/transactions", method = RequestMethod.POST)
    public Garden create(@RequestBody @Valid Garden garden) {
        if (garden == null) {
            throw new ResponseStatusException(HttpStatus.CREATED, "Garden Created");
        } else {
            return gardenDao.createGarden(garden);
        }
    }
}
