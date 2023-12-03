package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcGardenDao implements GardenDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcGardenDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Garden getGardenById(int gardenId) {
        Garden garden = null;
        String sql = "SELECT plant_id, plantname, password_hash, role FROM plants WHERE plant_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gardenId);
            if (results.next()) {
                garden = mapRowToGarden(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return garden;
    }

    @Override
    public List<Garden> getGarden() {
        List<Garden> gardens = new ArrayList<>();
        String sql = "SELECT plant_id, plantname, password_hash, role FROM plants";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Garden garden = mapRowToGarden(results);
                gardens.add(garden);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return gardens;
    }

    private Garden mapRowToGarden(SqlRowSet rs) {
        Garden garden = new Garden();
        garden.setGardenId(rs.getInt("garden_id"));
        garden.setGardenName(rs.getString("garden_name"));
        return garden;
    }
}
