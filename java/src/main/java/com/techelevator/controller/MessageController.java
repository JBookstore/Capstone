package com.techelevator.controller;

import com.techelevator.dao.MessageDao;
import com.techelevator.model.Event;
import com.techelevator.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class MessageController {

    private final MessageDao messageDao;

    public MessageController(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/messages/{userId}")
    public List<Message> getMessageByUserId(@PathVariable int id) {
        List<Message> message = messageDao.getMessageByUserId(id);
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message Not Found");
        } else {
            return messageDao.getMessageByUserId(id);
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/messages", method = RequestMethod.POST)
    public Message create(@RequestBody @Valid Message message) {
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.CREATED, "Message Created");
        } else {
            return messageDao.createMessage(message);
        }
    }
}
