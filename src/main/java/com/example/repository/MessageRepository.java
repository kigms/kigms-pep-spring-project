package com.example.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    ArrayList<Message> findAll();
    Message findByMessageId(Integer messageId);

    @Modifying
    @Query("DELETE FROM Message m WHERE m.messageId = :messageId")
    Integer deleteByMessageId(@Param("messageId") Integer messageId);

    @Modifying
    @Query("UPDATE Message m SET m.messageText = :messageText WHERE m.messageId = :messageId")
    Integer updateByMessageId(@Param("messageId") Integer messageId, String messageText);
    
    ArrayList<Message> findAllByPostedBy(Integer postedBy);
}
