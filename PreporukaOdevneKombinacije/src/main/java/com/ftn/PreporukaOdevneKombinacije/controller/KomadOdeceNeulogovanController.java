package com.ftn.PreporukaOdevneKombinacije.controller;


import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.dto.UnosNeulogovanDTO;
import com.ftn.PreporukaOdevneKombinacije.helper.GornjiDeoMapper;
import com.ftn.PreporukaOdevneKombinacije.helper.PreporuceniKomadiMapper;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Boja;
import com.ftn.PreporukaOdevneKombinacije.model.enums.DressCode;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Vreme;
import com.ftn.PreporukaOdevneKombinacije.service.KomadOdeceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/clothes-unlogged")
public class KomadOdeceNeulogovanController {

    @Autowired
    private KomadOdeceService komadOdeceService;

    private PreporuceniKomadiMapper preporuceniKomadiMapper;


    @PostMapping("/recommendation")
    public ResponseEntity<?> getPreporukaPersonalizovano(@RequestBody UnosNeulogovanDTO unosDTO) {
        //User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //User user = userService.findOne(1L);
        PreporuceniKomadi prep = komadOdeceService.getPreporukaOpste(unosDTO);
        if(prep==null){
            return new ResponseEntity<>("Error!", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(preporuceniKomadiMapper.toDto(prep),HttpStatus.OK);
        }
    }

    public KomadOdeceNeulogovanController(){
        preporuceniKomadiMapper = new PreporuceniKomadiMapper();
    }

}
