package com.example.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.service.*;
import com.example.entity.*;
import com.example.exception.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    // After the scanning of the RestController component,
    // Spring creates bean for accountService and injects
    // it into this class as a dependency via the
    // class constructor.
    private final AccountService accountService;
    private final MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> postUserAccount(@RequestBody Account account){
        try{
            // 
            Account created = accountService.registerAccount(account);
            return ResponseEntity.ok(created);
        } catch(DuplicateUsernameException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch(InvalidRegistrationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> postUserLoginCredentials(@RequestBody Account account){
        try{
            // 
            Account loginCredentialsVerified = accountService.verifyLoginCredentials(account);
            return ResponseEntity.ok(loginCredentialsVerified);
        } catch(InvalidLoginCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/messages")
    public ResponseEntity<?> postUserMessage(@RequestBody Message message){
        try{
            // 
            Message created = messageService.persistMessage(message);
            return ResponseEntity.ok(created);
        } catch(InvalidMessageException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(MessagePosterNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }    

    @GetMapping("/messages")
    public ResponseEntity<?> getAllMessages(){
        ArrayList<Message> found = messageService.getAllMessages();
        return ResponseEntity.ok(found);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<?> getMessageById(@RequestParam String messageId){
        Message message = messageService.get
    }
}