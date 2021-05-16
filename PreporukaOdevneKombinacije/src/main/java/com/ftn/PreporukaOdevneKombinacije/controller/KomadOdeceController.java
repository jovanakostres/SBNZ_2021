package com.ftn.PreporukaOdevneKombinacije.controller;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.helper.PreporuceniKomadiMapper;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Boja;
import com.ftn.PreporukaOdevneKombinacije.model.enums.DressCode;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Vreme;
import com.ftn.PreporukaOdevneKombinacije.service.KomadOdeceService;
import com.ftn.PreporukaOdevneKombinacije.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/clothes")
public class KomadOdeceController {

    @Autowired
    private UserService userService;

    @Autowired
    private KomadOdeceService komadOdeceService;

    private PreporuceniKomadiMapper preporuceniKomadiMapper;

    @PostMapping("/personalized_recommendation")
    public ResponseEntity<?> getPreporukaPersonalizovano(@RequestBody UnosDTO unosDTO) {
        User user = userService.findOne(1L);
        PreporuceniKomadi prep = komadOdeceService.getPreporukaPersonalizovano(new UnosDTO(25, Vreme.SUVO, "Novi Sad", DressCode.LEZERAN, new ArrayList<Boja>() {{ add(Boja.LJUBICASTA); add(Boja.BELA);}}), user);
        if(prep.getPreporuceniGornjiDelovi().size() <= 0){
            return new ResponseEntity<>("Error! Comment not found!", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(preporuceniKomadiMapper.toDto(prep),HttpStatus.OK);
        }
    }

    public KomadOdeceController(){
        preporuceniKomadiMapper = new PreporuceniKomadiMapper();
    }
}
