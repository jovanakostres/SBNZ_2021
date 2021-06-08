package com.ftn.PreporukaOdevneKombinacije.controller;


import com.ftn.PreporukaOdevneKombinacije.dto.OdecaAddAdminDTO;
import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.dto.UnosNeulogovanDTO;
import com.ftn.PreporukaOdevneKombinacije.helper.GornjiDeoMapper;
import com.ftn.PreporukaOdevneKombinacije.helper.KomadiMapper;
import com.ftn.PreporukaOdevneKombinacije.helper.PreporuceniKomadiMapper;
import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
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

    @Autowired
    private UserService userService;

    private PreporuceniKomadiMapper preporuceniKomadiMapper;

    private KomadiMapper komadiMapper;


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

    @PostMapping("/add")
    public ResponseEntity<?> addKomadOdece(@RequestBody OdecaAddAdminDTO unosDTO) {
        User user = userService.findOne(2L);
        KomadOdece ko = komadiMapper.toEntity(unosDTO);
        ko.setKorisnik(user);
        try{
            komadOdeceService.addKomad(ko);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>("Error!", HttpStatus.BAD_REQUEST);
        }
    }

    public KomadOdeceNeulogovanController(){
        komadiMapper = new KomadiMapper();
        preporuceniKomadiMapper = new PreporuceniKomadiMapper();
    }

}
