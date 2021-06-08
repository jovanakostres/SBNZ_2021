package com.ftn.PreporukaOdevneKombinacije.controller;

import com.ftn.PreporukaOdevneKombinacije.dto.*;
import com.ftn.PreporukaOdevneKombinacije.helper.GornjiDeoMapper;
import com.ftn.PreporukaOdevneKombinacije.helper.PreporuceniKomadiMapper;
import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PodaciIzvestaj;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniGornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Boja;
import com.ftn.PreporukaOdevneKombinacije.model.enums.DressCode;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Vreme;
import com.ftn.PreporukaOdevneKombinacije.service.GornjiDeoService;
import com.ftn.PreporukaOdevneKombinacije.service.KomadOdeceService;
import com.ftn.PreporukaOdevneKombinacije.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/clothes")
public class KomadOdeceController {

    @Autowired
    private UserService userService;

    @Autowired
    private KomadOdeceService komadOdeceService;

    @Autowired
    private GornjiDeoService gornjiDeoService;

    private PreporuceniKomadiMapper preporuceniKomadiMapper;

    private GornjiDeoMapper gornjiDeoMapper;

    @PostMapping("/personalized_recommendation")
    public ResponseEntity<?> getPreporukaPersonalizovano(@RequestBody UnosDTO unosDTO) {
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //User user = userService.findOne(1L);
        PreporuceniKomadi prep = komadOdeceService.getPreporukaPersonalizovano(new UnosDTO(25, Vreme.SUVO, "Novi Sad", DressCode.LEZERAN, new ArrayList<Boja>() {{ add(Boja.LJUBICASTA); add(Boja.BELA);}}), userDetails);
        if(prep.getPreporuceniGornjiDelovi().size() <= 0){
            return new ResponseEntity<>("Error!", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(preporuceniKomadiMapper.toDto(prep),HttpStatus.OK);
        }
    }

    @PostMapping("/reject")
    public ResponseEntity<?> rejectClothes(@RequestBody PreporuceniGornjiDeoDTO preporuceniGornjiDeo) {
        try {
            User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //User user = userService.findOne(1L);
            KomadOdece komadOdece = komadOdeceService.findOne(preporuceniGornjiDeo.getId());
            komadOdece.setKoeficijentOdabira(komadOdece.getKoeficijentOdabira() - komadOdece.getKoeficijentOdabira() * 3 / 100);
            komadOdeceService.odbijenKomad(komadOdece);
            //komadOdeceService.create(komadOdece);
            return new ResponseEntity<>(komadOdece.getKoeficijentOdabira(),HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Error!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/accepted")
    public ResponseEntity<?> postOdabrano(@RequestBody IzabranoDTO izabranoDTO) {
        try {
            System.out.println("ovde");
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //User user = userService.findOne(1L);
        komadOdeceService.izabraniKomadi(izabranoDTO);
        komadOdeceService.izabraniKomadiKomb(izabranoDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Error!", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("upper_part")
    public ResponseEntity<?> createGornjiDeo(@RequestBody GornjiDeoUnosDTO gornjiDeoUnosDTO) {
        try {
            User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            GornjiDeo gornjiDeo = gornjiDeoMapper.toEntity(gornjiDeoUnosDTO);
            gornjiDeo.setKorisnik(userDetails);
            GornjiDeo created = gornjiDeoService.create(gornjiDeo);
            userDetails.getKomadi().add(created);
            userService.update(userDetails);
            return new ResponseEntity<>(
                    gornjiDeoMapper.toDto(created),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(
                    "Error! Gornji Deo not created!",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/report24")
    public ResponseEntity<?> getKombinacije24() {
        try {
            User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            PodaciIzvestaj podaciIzvestaj = komadOdeceService.get24Report(userDetails);
            komadOdeceService.deleteListPreporucenih();
            return new ResponseEntity<>(
                    new PodaciIzvestajDTO(preporuceniKomadiMapper.toDto(podaciIzvestaj.getPreporuceniKomadi()), podaciIzvestaj.getVremeDTO().getListaVremena() ),
                    HttpStatus.OK);
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(
                    "Error!",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/report7Most")
    public ResponseEntity<?> get7NajvisePreporucivano() {
        try {
            User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            PreporuceniKomadi preporuceniKomadi = komadOdeceService.get7Most(userDetails);
            komadOdeceService.deleteListPreporucenih();
            return new ResponseEntity<>(
                    preporuceniKomadiMapper.toDto(preporuceniKomadi),
                    HttpStatus.OK);
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(
                    "Error!",
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/report7")
    public ResponseEntity<?> getKombinacije7() {
        try {
            User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            PodaciIzvestaj podaciIzvestaj = komadOdeceService.get7Report(userDetails);
            komadOdeceService.deleteListPreporucenih();
            return new ResponseEntity<>(
                    new PodaciIzvestajDTO(preporuceniKomadiMapper.toDto(podaciIzvestaj.getPreporuceniKomadi()), podaciIzvestaj.getVremeDTO().getListaVremena() ),
                    HttpStatus.OK);
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(
                    "Error!",
                    HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getGornjiDeo(@PathVariable("id") Long gornjiDeoId) {
        GornjiDeo gornjiDeo = gornjiDeoService.findOne(gornjiDeoId);
        if(gornjiDeo == null){
            return new ResponseEntity<>("Error! Comment not found!",HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(gornjiDeoMapper.toDto(gornjiDeo),HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<?> getComments() {
        List<GornjiDeo> gornjiDeoList = gornjiDeoService.findAll();
        return new ResponseEntity<>(toGDDtoList(gornjiDeoList), HttpStatus.OK);
    }


    private List<GornjiDeoUnosDTO> toGDDtoList(List<GornjiDeo> gornjiDeos) {
        List<GornjiDeoUnosDTO> gornjiDeoUnosDTOS = new ArrayList<>();
        for(GornjiDeo gornjiDeo : gornjiDeos){
            gornjiDeoUnosDTOS.add(gornjiDeoMapper.toDto(gornjiDeo));
        }
        return gornjiDeoUnosDTOS;
    }

    public KomadOdeceController(){
        preporuceniKomadiMapper = new PreporuceniKomadiMapper();
        gornjiDeoMapper = new GornjiDeoMapper();
    }
}
