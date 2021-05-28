package com.ftn.PreporukaOdevneKombinacije.model.drlModel;

import com.ftn.PreporukaOdevneKombinacije.model.DonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.Jakna;

public class PreporucenaJakna {

    private Jakna jakna;
    private Double poeni;

    public PreporucenaJakna(Jakna jakna, Double poeni) {
        this.jakna = jakna;
        this.poeni = poeni;
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
