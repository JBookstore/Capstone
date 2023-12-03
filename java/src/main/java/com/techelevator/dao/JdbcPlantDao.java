package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Plant;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcPlantDao implements PlantDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcPlantDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Plant getPlantById(int plantId) {
        Plant plant = null;
        String sql = "SELECT plant_id, plantname, password_hash, role FROM plants WHERE plant_id = ?";
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
        String sql = "SELECT plant_id, plantname, password_hash, role FROM plants";
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
        plant.setPlantId(rs.getInt("plant_id"));
        plant.setPlantName(rs.getString("plant_name"));
        return plant;
    }
}
