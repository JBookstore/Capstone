package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Event;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcEventDao implements EventDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcEventDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Event getEventById(int eventId) {
        Event event = null;
        String sql = "SELECT * FROM garden_event WHERE event_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
            if (results.next()) {
                event = mapRowToEvent(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return event;
    }

    @Override
    public List<Event> getEvent() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM garden_event;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Event event = mapRowToEvent(results);
                events.add(event);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return events;
    }

    @Override
    public Event createEvent(Event event) {
        Event newEvent = null;
        String sql = "INSERT INTO garden_event (garden_id, user_id, event_description, event_coordinator," +
                " user_volunteer, childcare_owner, event_date, event_category)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            int eventId = jdbcTemplate.queryForObject(sql, int.class, event.getGardenId(), event.getUserId(), event.getEventDescription(),
                    event.getEventCoordinator(), event.getUserVolunteer(), event.getChildcareOwner(), event.getEventDate(), event.getEventCategory());
            newEvent = getEventById(eventId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation");
        }
        return newEvent;
    }

    private Event mapRowToEvent(SqlRowSet rs) {
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setGardenId(rs.getInt("garden_id"));
        event.setUserId(rs.getInt("user_id"));
        event.setEventDescription(rs.getString("event_description"));
        event.setEventCoordinator(rs.getString("event_coordinator"));
        event.setUserVolunteer(rs.getString("user_volunteer"));
        event.setChildcareOwner(rs.getString("childcare_owner"));
        event.setEventDate(rs.getString("event_date"));
        event.setEventCategory(rs.getString("event_category"));
        return event;
    }
}
