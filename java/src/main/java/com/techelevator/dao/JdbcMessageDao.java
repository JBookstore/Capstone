package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Message;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcMessageDao implements MessageDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcMessageDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Message> getMessageByUserId(int messageId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM user_messages WHERE to_user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, messageId);
            while (results.next()) {
                Message message = mapRowToMessage(results);
                messages.add(message);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return messages;
    }

    @Override
    public Message getMessageById(int messageId) {
        Message message = null;
        String sql = "SELECT * FROM user_messages WHERE message_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, messageId);
            if (results.next()) {
                message = mapRowToMessage(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return message;
    }

    @Override
    public Message createMessage(Message message) {
        Message newMessage = null;
        String sql = "INSERT INTO user_messages (to_user_id, from_user_id, message_body, date_and_time, is_read)" +
                "VALUES (?, ?, ?, ?, ?) RETURNING message_id;";
        try {
            int messageId = jdbcTemplate.queryForObject(sql, int.class, message.getToUserId(), message.getFromUserId(),
                    message.getMessageBody(), message.getDateAndTime(), message.getRead());
            newMessage = getMessageById(messageId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation");
        }
        return newMessage;
    }

    private Message mapRowToMessage(SqlRowSet rs) {
        Message message = new Message();
        message.setMessageId(rs.getInt("message_id"));
        message.setToUserId(rs.getInt("to_user_id"));
        message.setFromUserId(rs.getInt("from_user_id"));
        message.setMessageBody(rs.getString("message_body"));
        message.setDateAndTime(rs.getString("date_and_time"));
        message.setRead(rs.getBoolean("is_read"));
        return message;
    }
}
