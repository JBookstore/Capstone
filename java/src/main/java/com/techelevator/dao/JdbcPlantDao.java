package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Plant;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPlantDao implements PlantDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPlantDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Plant getPlantById(int plantId) {
        Plant plant = null;
        String sql = "SELECT plant_id, common_name, scientific_name, other_name, watering, " +
                " regular_img_url, plant_description, api_plant_id FROM plant WHERE plant_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, plantId);
            if (results.next()) {
                plant = mapRowToPlant(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return plant;
    }

    @Override
    public List<Plant> getPlants() {
        List<Plant> startingplants = new ArrayList<>();
        List<Plant> finalPlantsList = new ArrayList<>();
        List<String> runningSunshineList = new ArrayList<>();
        String sql = "SELECT \n" +
                "    p.plant_id, \n" +
                "    p.common_name, \n" +
                "    p.scientific_name, \n" +
                "    p.other_name, \n" +
                "    p.watering, \n" +
                "    p.regular_img_url, \n" +
                "    p.plant_description, \n" +
                "    s.sunshine_description, \n" +
                "    p.api_plant_id \n" +
                "FROM \n" +
                "    sunshine s\n" +
                "LEFT JOIN \n" +
                "    plant p ON p.plant_id = s.plant_id;\n";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Plant plant = mapRowToPlant(results);
                startingplants.add(plant);
            }
            if (startingplants.size() < 2) {
                runningSunshineList.add(startingplants.get(0).getSunshineDescription());
                startingplants.get(0).setSunlight(runningSunshineList);
                return startingplants;
            } else {
                int nextPlantId = startingplants.get(0 + 1).getId();
                int plantListIndex = 0;
                for (int i = 0; i < startingplants.size(); i++) {
                    if (i == 0){
                        finalPlantsList.add(startingplants.get(0));
                        runningSunshineList.add(startingplants.get(0).getSunshineDescription());
                        nextPlantId = startingplants.get(i + 1).getId();
                    }
                    else if (startingplants.get(i).getSunshineDescription().isEmpty()){
                        finalPlantsList.add(startingplants.get(i));
                        plantListIndex += 1;
                        finalPlantsList.get(plantListIndex).setSunlight(new ArrayList<>());
                    }
                    else if (i == startingplants.size() - 1 && nextPlantId == startingplants.get(i).getId()){
                        runningSunshineList.add(startingplants.get(i).getSunshineDescription());
                        finalPlantsList.get(plantListIndex).setSunlight(runningSunshineList);
                        return finalPlantsList;
                    }
                    else if (nextPlantId == startingplants.get(i).getId()){
                        runningSunshineList.add(startingplants.get(i).getSunshineDescription());
                        nextPlantId = startingplants.get(i + 1).getId();
                        if(nextPlantId != startingplants.get(i).getId()){
                            finalPlantsList.get(plantListIndex).setSunlight(new ArrayList<>(runningSunshineList));
                            finalPlantsList.add(startingplants.get(i + 1));
                            plantListIndex += 1;
                            runningSunshineList.clear();
                        }
                    }
                }
            }

//            else {
//                int nextPlantId = startingplants.get(0 + 1).getId();
//                finalPlantsList.add(startingplants.get(0));
//                for (int i = 0; i < startingplants.size(); i++) {
//                    if (nextPlantId == startingplants.get(i).getId()) {
//                        runningSunshineList.add(startingplants.get(i).getSunshineDescription());
//                        nextPlantId = startingplants.get(i + 1).getId();
//                    }
//                    if (previousPlantId < startingplants.get(i).getId() && i < startingplants.size() - 1) {
//                        //finalPlantsList.get(previousPlantId).setSunlight(runningSunshineList);
//                        runningSunshineList.clear();
//                        runningSunshineList.add(startingplants.get(i).getSunshineDescription());
//                        finalPlantsList.add(startingplants.get(i));
//                        previousPlantId = startingplants.get(i).getId();
//                    }
//                    if (i == startingplants.size() - 1) {
//                        finalPlantsList.add(startingplants.get(i));
//                        finalPlantsList.get(previousPlantId).setSunlight(runningSunshineList);
//                        runningSunshineList.clear();
//                    }
//                }
//            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return finalPlantsList;
    }

    private Plant mapRowToPlant(SqlRowSet rs) {
        Plant plant = new Plant();
        plant.setId(rs.getInt("plant_id"));
        plant.setCommonName(rs.getString("common_name"));
        plant.setScientificName(rs.getString("scientific_name"));
        plant.setOtherName(rs.getString("other_name"));
        plant.setWatering(rs.getString("watering"));
        plant.setSunshineDescription(rs.getString("sunshine_description"));
        plant.setImgUrl(rs.getString("regular_img_url"));
        plant.setDescription(rs.getString("plant_description"));
        plant.setApiPlantId(rs.getInt("api_plant_id"));

        return plant;
    }
}
