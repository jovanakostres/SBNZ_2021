package com.ftn.PreporukaOdevneKombinacije.helper;

import com.ftn.PreporukaOdevneKombinacije.dto.*;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.*;

import java.util.ArrayList;
import java.util.List;

public class PreporuceniKomadiOpsteMapper implements MapperInterface<PreporuceniKomadi, PreporuceniKomadiOpsteDTO>{
    @Override
    public PreporuceniKomadi toEntity(PreporuceniKomadiOpsteDTO dto) {
        return null;
    }

    @Override
    public PreporuceniKomadiOpsteDTO toDto(PreporuceniKomadi entity) {
        List<PreporuceniGornjiDeoDTO> preporuceniGornjiDelovi = new ArrayList<>();
        List<PreporuceniDonjiDeoDTO> preporuceniDonjiDeolovi = new ArrayList<>();
        List<PreporucenaJaknaDTO> preporuceneJakne = new ArrayList<>();
        List<PreporucenaObucaDTO> preporucenaObucaa = new ArrayList<>();

        for(PreporuceniGornjiDeo preporuceniGornjiDeo : entity.getPreporuceniGornjiDelovi()){
            preporuceniGornjiDelovi.add(new PreporuceniGornjiDeoDTO(preporuceniGornjiDeo.getGornjiDeo().getId(), preporuceniGornjiDeo.getGornjiDeo().getImage(), preporuceniGornjiDeo.getPoeni()));
        }
        for(PreporuceniDonjiDeo preporuceniDonjiDeo : entity.getPreporuceniDonjiDelovi()){
            preporuceniDonjiDeolovi.add(new PreporuceniDonjiDeoDTO(preporuceniDonjiDeo.getDonjiDeo().getId(), preporuceniDonjiDeo.getDonjiDeo().getImage(), preporuceniDonjiDeo.getPoeni()));
        }

        for(PreporucenaJakna preporucenaJakna : entity.getPreporuceneJakne()){
            preporuceneJakne.add(new PreporucenaJaknaDTO(preporucenaJakna.getJakna().getId(), preporucenaJakna.getJakna().getImage(), preporucenaJakna.getPoeni()));
        }

        for(PreporucenaObuca preporucenaObuca : entity.getPreporucenaObuca()){
            preporucenaObucaa.add(new PreporucenaObucaDTO(preporucenaObuca.getObuca().getId(), preporucenaObuca.getObuca().getImage(), preporucenaObuca.getPoeni()));
        }

        return new PreporuceniKomadiOpsteDTO(preporuceniGornjiDelovi, preporuceniDonjiDeolovi, preporuceneJakne, preporucenaObucaa);
    }
}
