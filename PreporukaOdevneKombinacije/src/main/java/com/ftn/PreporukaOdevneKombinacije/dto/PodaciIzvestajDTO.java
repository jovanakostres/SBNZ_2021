package com.ftn.PreporukaOdevneKombinacije.dto;

import java.util.ArrayList;

public class PodaciIzvestajDTO {

    private PreporuceniKomadiDTO preporuceniKomadi;
    private ArrayList<String> vreme;

    public PodaciIzvestajDTO() {

    }

    public PodaciIzvestajDTO(PreporuceniKomadiDTO preporuceniKomadi, ArrayList<String> vreme) {
        this.preporuceniKomadi = preporuceniKomadi;
        this.vreme = vreme;
    }

    public PreporuceniKomadiDTO getPreporuceniKomadiDTO() {
        return preporuceniKomadi;
    }

    public void setPreporuceniKomadi(PreporuceniKomadiDTO preporuceniKomadi) {
        this.preporuceniKomadi = preporuceniKomadi;
    }

    public ArrayList<String> getVreme() {
        return vreme;
    }

    public void setVreme(ArrayList<String> vreme) {
        this.vreme = vreme;
    }
}
