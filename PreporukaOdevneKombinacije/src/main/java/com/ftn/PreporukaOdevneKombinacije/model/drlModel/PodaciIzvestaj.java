package com.ftn.PreporukaOdevneKombinacije.model.drlModel;

import com.ftn.PreporukaOdevneKombinacije.dto.PreporuceniKomadiDTO;
import com.ftn.PreporukaOdevneKombinacije.dto.VremeDTO;

public class PodaciIzvestaj {

    private PreporuceniKomadi preporuceniKomadi;
    private VremeDTO vremeDTO;

    public PodaciIzvestaj() {

    }

    public PodaciIzvestaj(PreporuceniKomadi preporuceniKomadiDTO, VremeDTO vremeDTO) {
        this.preporuceniKomadi = preporuceniKomadiDTO;
        this.vremeDTO = vremeDTO;
    }

    public PreporuceniKomadi getPreporuceniKomadi() {
        return preporuceniKomadi;
    }

    public void setPreporuceniKomadiDTO(PreporuceniKomadi preporuceniKomadiDTO) {
        this.preporuceniKomadi = preporuceniKomadiDTO;
    }

    public VremeDTO getVremeDTO() {
        return vremeDTO;
    }

    public void setVremeDTO(VremeDTO vremeDTO) {
        this.vremeDTO = vremeDTO;
    }
}
