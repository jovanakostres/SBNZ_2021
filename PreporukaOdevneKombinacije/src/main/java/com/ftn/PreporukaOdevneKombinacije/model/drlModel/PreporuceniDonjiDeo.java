package com.ftn.PreporukaOdevneKombinacije.model.drlModel;

import com.ftn.PreporukaOdevneKombinacije.model.DonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;

public class PreporuceniDonjiDeo {

    private DonjiDeo donjiDeo;
    private Double poeni;

    public PreporuceniDonjiDeo(DonjiDeo donjiDeo, Double poeni) {
        this.donjiDeo = donjiDeo;
        this.poeni = poeni;
    }

    public PreporuceniDonjiDeo(KomadOdece gornjiDeo, Integer poeni) {
        this.donjiDeo = (DonjiDeo) gornjiDeo;
        this.poeni = (double)poeni;
    }

    public PreporuceniDonjiDeo(Object gornjiDeo) {
        this.donjiDeo = (DonjiDeo) gornjiDeo;
    }

    public DonjiDeo getDonjiDeo() {
        return donjiDeo;
    }

    public void setDonjiDeo(DonjiDeo donjiDeo) {
        this.donjiDeo = donjiDeo;
    }

    public Double getPoeni() {
        return poeni;
    }

    public void setPoeni(Double poeni) {
        this.poeni = poeni;
    }
}
