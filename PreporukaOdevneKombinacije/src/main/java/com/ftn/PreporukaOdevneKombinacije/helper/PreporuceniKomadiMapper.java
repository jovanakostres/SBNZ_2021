package com.ftn.PreporukaOdevneKombinacije.helper;

import com.ftn.PreporukaOdevneKombinacije.dto.PreporuceniGornjiDeoDTO;
import com.ftn.PreporukaOdevneKombinacije.dto.PreporuceniKomadiDTO;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniDonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniGornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;

import java.util.ArrayList;
import java.util.List;

public class PreporuceniKomadiMapper implements MapperInterface<PreporuceniKomadi, PreporuceniKomadiDTO> {


    @Override
    public PreporuceniKomadi toEntity(PreporuceniKomadiDTO dto) {
        return null;
    }

    @Override
    public PreporuceniKomadiDTO toDto(PreporuceniKomadi entity) {
        List<PreporuceniGornjiDeoDTO> preporuceniGornjiDelovi = new ArrayList<>();
        for(PreporuceniGornjiDeo preporuceniGornjiDeo : entity.getPreporuceniGornjiDelovi()){
            preporuceniGornjiDelovi.add(new PreporuceniGornjiDeoDTO(preporuceniGornjiDeo.getGornjiDeo().getId(), preporuceniGornjiDeo.getGornjiDeo().getImage(), preporuceniGornjiDeo.getPoeni()));
        }
        for(PreporuceniDonjiDeo preporuceniDonjiDeo : entity.getPreporuceniDonjiDelovi()){
            preporuceniGornjiDelovi.add(new PreporuceniGornjiDeoDTO(preporuceniDonjiDeo.getDonjiDeo().getId(), preporuceniDonjiDeo.getDonjiDeo().getImage(), preporuceniDonjiDeo.getPoeni()));
        }
        return new PreporuceniKomadiDTO(preporuceniGornjiDelovi);
    }
}
