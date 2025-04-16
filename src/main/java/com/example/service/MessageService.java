package com.example.service;

import com.example.entity.Message;
import com.example.exception.*;
import com.example.repository.MessageRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message persistMessage(Message message){
        if(message.getMessageText().isBlank())
            throw new InvalidMessageException();
        if(message.getMessageText().length() > 255)
             throw new InvalidMessageException();
        if(messageRepository.existsById(message.getPostedBy()))
            return messageRepository.save(message);
        else
            throw new MessagePosterNotFoundException();
    }

    public ArrayList<Message> getAllMessages(){
        ArrayList<Message> found = messageRepository.findAll();
        return found;
    }
}
