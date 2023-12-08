package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
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
        String sqlEvent = "INSERT INTO" +
                            " garden_event (" +
                                " garden_id," +
                                " user_id," +
                                " event_name," +
                                " event_description," +
                                " event_coordinator," +
                                " childcare_owner," +
                                " event_date," +
                                " event_category)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?)" +
                            " RETURNING event_id;";

        String sqlVolunteer = "INSERT INTO volunteer (event_id, volunteer_name) VALUES (?,?);";

        try {
            int eventId = jdbcTemplate.queryForObject(sqlEvent, int.class, event.getGardenId(), event.getUserId(), event.getEventName(), event.getEventDescription(),
                    event.getEventCoordinator(), event.getChildcareOwner(), event.getEventDate(), event.getEventCategory());
            newEvent = getEventById(eventId);

            for (int i = 0; i < event.getVolunteer().size(); i++) {
                jdbcTemplate.update(sqlVolunteer,eventId, event.getVolunteer().get(i));
            }
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
        event.setEventName(rs.getString("event_name"));
        event.setEventDescription(rs.getString("event_description"));
        event.setEventCoordinator(rs.getString("event_coordinator"));
        event.setChildcareOwner(rs.getString("childcare_owner"));
        event.setEventDate(rs.getString("event_date"));
        event.setEventCategory(rs.getString("event_category"));
        return event;
    }

    private Volunteer mapRowToVolunteer(SqlRowSet rs){
        Volunteer volunteer = new Volunteer();
        volunteer.setVolunteerId(rs.getInt("volunteer_id"));
        volunteer.setEventId(rs.getInt("event_id"));
        volunteer.setVolunteerName(rs.getString("volunteer_name"));
        return volunteer;
    }
}
