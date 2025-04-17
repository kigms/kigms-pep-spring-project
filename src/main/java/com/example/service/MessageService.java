package com.example.service;

import com.example.entity.Message;
import com.example.exception.*;
import com.example.repository.MessageRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Message getMessageById(Integer messageId){
        Message found = messageRepository.findByMessageId(messageId);
        return found;
    }

    @Transactional
    public Integer deleteMessageById(Integer messageId){
        Integer deleted = messageRepository.deleteByMessageId(messageId);
        return deleted;
    }

    @Transactional
    public Integer updateMessageById(Integer messageId, String messageText){
        if(messageText.isBlank())
            throw new InvalidMessageException();

        if(messageText.length() > 255)
            throw new InvalidMessageException();
            
        if(messageRepository.existsById(messageId)){
            Integer updated = messageRepository.updateByMessageId(messageId, messageText);
            return updated;
        }
        else
            throw new MessageNotFoundException();
    }

    public ArrayList<Message> getAllMessagesByAccountId(Integer accountId){
        ArrayList<Message> found = messageRepository.findAllByPostedBy(accountId);
        return found;
    }
}
