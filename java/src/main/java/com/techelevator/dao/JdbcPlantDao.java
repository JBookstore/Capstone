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
public class JdbcPlantDao implements PlantDao{
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
        List<Plant> plants = new ArrayList<>();
        String sql = "SELECT plant_id, common_name, scientific_name, other_name, watering, " +
                "regular_img_url, plant_description, sunshine_description, api_plant_id FROM plant_sunshine_view;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Plant plant = mapRowToPlant(results);
                plants.add(plant);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return plants;
    }

    private Plant mapRowToPlant(SqlRowSet rs) {
        Plant plant = new Plant();
        plant.setId(rs.getInt("plant_id"));
        plant.setCommonName(rs.getString("common_name"));
        plant.setScientificName(rs.getString("scientific_name"));
        plant.setOtherName(rs.getString("other_name"));
        plant.setWatering(rs.getString("watering"));
        //plant.setSunlight(rs.getString("sunshine_description"));
        plant.setImgUrl(rs.getString("regular_img_url"));
        plant.setDescription(rs.getString("plant_description"));
        plant.setApiPlantId(rs.getInt("api_plant_id"));

        return plant;
    }

//      String arrayAsString = rs.getString("sunlight");
//        //String[] sunlight = rs.getObject("sunlight", String[].class);
//        String sunny = "";
//        if (arrayAsString != null) {
//            String[] sunlight = arrayAsString.replace("{", "").replace("}", "").split(",");
//            for (int i = 0; i < sunlight.length; i++) {
//               sunny += sunlight[i] + " ";
//            }
//            plant.setSunlight(sunny);
//        }
}
