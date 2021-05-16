package com.ftn.PreporukaOdevneKombinacije.controller;

import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clothes")
public class KomadOdeceController {

    @Autowired
    private UserService userService;

    @GetMapping("/personalized_recommendation")
    public ResponseEntity<?> getComment() {
        User user = userService.findOne(1L);
        if(user != null){
            return new ResponseEntity<>("Error! Comment not found!", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
