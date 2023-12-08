package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Plant;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPlantDao implements PlantDao {
    private final JdbcTemplate jdbcTemplate;
    private String sql;
    private int lookUpId;
    private boolean isIdNeeded = false;

    public JdbcPlantDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Plant> getPlantById(int plantId) {
        String sql = "SELECT" +
                        " p.plant_id," +
                        " p.common_name," +
                        " p.scientific_name," +
                        " p.other_name," +
                        " p.watering," +
                        " p.regular_img_url," +
                        " p.plant_description," +
                        " p.api_plant_id," +
                        " s.sunshine_description" +
                    " FROM" +
                        " sunshine s" +
                    " LEFT JOIN" +
                        " plant p" +
                    " ON" +
                        " p.plant_id = s.plant_id" +
                    " WHERE" +
                        " p.plant_id = ?;";
        isIdNeeded = true;
        lookUpId = plantId;
        return getSunLightArray(sql, isIdNeeded);
    }

    @Override
    public List<Plant> getPlantByGardenId(int gardenId) {
        String sql = "SELECT" +
                        " p.plant_id," +
                        " p.common_name," +
                        " p.scientific_name," +
                        " p.other_name," +
                        " p.watering," +
                        " p.regular_img_url," +
                        " p.plant_description," +
                        " p.api_plant_id," +
                        " s.sunshine_description" +
                    " FROM" +
                        " plant p" +
                    " JOIN" +
                        " garden_plant gp ON p.plant_id = gp.plant_id" +
                    " JOIN" +
                        " sunshine s ON p.plant_id = s.plant_id" +
                    " WHERE" +
                        " gp.garden_id = ?;";
        isIdNeeded = true;
        lookUpId = gardenId;
        return getSunLightArray(sql, isIdNeeded);
    }

    @Override
    public List<Plant> getPlantByUserId(int userId) {
        String sql = "SELECT" +
                        " p.plant_id," +
                        " p.common_name," +
                        " p.scientific_name," +
                        " p.other_name," +
                        " p.watering," +
                        " p.regular_img_url," +
                        " p.plant_description," +
                        " p.api_plant_id," +
                        " s.sunshine_description" +
                    " FROM" +
                        " plant p" +
                    " JOIN" +
                        " plant_user pu ON p.plant_id = pu.plant_id" +
                    " JOIN" +
                        " sunshine s ON p.plant_id = s.plant_id" +
                    " WHERE" +
                        " pu.user_id = ?;";
        isIdNeeded = true;
        lookUpId = userId;
        return getSunLightArray(sql, isIdNeeded);
    }

    @Override
    public List<Plant> getPlants() {

        String sql = "SELECT" +
                        " p.plant_id," +
                        " p.common_name," +
                        " p.scientific_name," +
                        " p.other_name," +
                        " p.watering," +
                        " p.regular_img_url," +
                        " p.plant_description," +
                        " p.api_plant_id," +
                        " s.sunshine_description" +
                    " FROM" +
                        " sunshine s" +
                    " LEFT JOIN" +
                        " plant p" +
                    " ON" +
                        " p.plant_id = s.plant_id;";

        isIdNeeded = false;
        return getSunLightArray(sql, isIdNeeded);
    }

    @Override
    public List<Plant> createPlant(Plant plant) {
        int plantId;

        String sqlPlant = "INSERT INTO" +
                            " plant (" +
                                " common_name," +
                                " scientific_name," +
                                " other_name," +
                                " watering," +
                                " regular_img_url," +
                                " plant_description," +
                                " api_plant_id)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?)" +
                            " RETURNING plant_id;";

        String sqlSunshine = "INSERT INTO sunshine (plant_id, sunshine_description) VALUES (?,?);";


        try {
            plantId = jdbcTemplate.queryForObject(sqlPlant, int.class, plant.getCommonName(), plant.getScientificName(),
                    plant.getOtherName(), plant.getWatering(), plant.getImgUrl(), plant.getDescription(), plant.getApiPlantId());

            for (int i = 0; i < plant.getSunlight().size(); i++) {
                jdbcTemplate.update(sqlSunshine,plantId, plant.getSunlight().get(i));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation");
        }
        List<Plant> newPlant = new ArrayList<>(getPlantById(plantId));
        return newPlant;
    }

    private List<Plant> getSunLightArray (String sql, Boolean isIdNeeded){
        List<Plant> startingPlants = new ArrayList<>();
        List<Plant> finalPlantsList = new ArrayList<>();
        List<String> runningSunshineList = new ArrayList<>();
        SqlRowSet results;
        this.sql = sql;
        try {
            if(!isIdNeeded) {
                results = jdbcTemplate.queryForRowSet(sql);
            }
            else {
                results = jdbcTemplate.queryForRowSet(sql, lookUpId);
            }
            while (results.next()) {
                Plant plant = mapRowToPlant(results);
                startingPlants.add(plant);
            }
            if (startingPlants.isEmpty()) {
                return startingPlants;
            }
            else if (startingPlants.size() == 1) {
                runningSunshineList.add(startingPlants.get(0).getSunshineDescription());
                startingPlants.get(0).setSunlight(runningSunshineList);
                return startingPlants;
            } else {
                int nextPlantId = startingPlants.get(0 + 1).getId();
                int plantListIndex = 0;
                for (int i = 0; i < startingPlants.size(); i++) {
                    if (i == 0){
                        finalPlantsList.add(startingPlants.get(0));
                        runningSunshineList.add(startingPlants.get(0).getSunshineDescription());
                        nextPlantId = startingPlants.get(i + 1).getId();
                    }
                    else if (startingPlants.get(i).getSunshineDescription().isEmpty()){
                        finalPlantsList.add(startingPlants.get(i));
                        plantListIndex += 1;
                        finalPlantsList.get(plantListIndex).setSunlight(new ArrayList<>());
                    }
                    else if (i == startingPlants.size() - 1 && nextPlantId == startingPlants.get(i).getId()){
                        runningSunshineList.add(startingPlants.get(i).getSunshineDescription());
                        finalPlantsList.get(plantListIndex).setSunlight(runningSunshineList);
                        return finalPlantsList;
                    }
                    else if (nextPlantId == startingPlants.get(i).getId()){
                        runningSunshineList.add(startingPlants.get(i).getSunshineDescription());
                        nextPlantId = startingPlants.get(i + 1).getId();
                        if(nextPlantId != startingPlants.get(i).getId()){
                            finalPlantsList.get(plantListIndex).setSunlight(new ArrayList<>(runningSunshineList));
                            finalPlantsList.add(startingPlants.get(i + 1));
                            plantListIndex += 1;
                            runningSunshineList.clear();
                        }
                    }
                }
            }
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
