package com.ftn.PreporukaOdevneKombinacije.model.drlModel;

import com.ftn.PreporukaOdevneKombinacije.model.DonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.Jakna;
import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;

public class PreporucenaJakna {

    private Jakna jakna;
    private Double poeni;

    public PreporucenaJakna(Jakna jakna, Double poeni) {
        this.jakna = jakna;
        this.poeni = poeni;
    }

    public PreporucenaJakna(KomadOdece jakna, Integer poeni) {
        this.jakna = (Jakna) jakna;
        this.poeni = (double) poeni;
    }

    public PreporucenaJakna(Object jakna) {
        this.jakna = (Jakna) jakna;
    }

    public Jakna getJakna() {
        return jakna;
    }

    public void setJakna(Jakna jakna) {
        this.jakna = jakna;
    }

    public Double getPoeni() {
        return poeni;
    }

    public void setPoeni(Double poeni) {
        this.poeni = poeni;
    }
}
