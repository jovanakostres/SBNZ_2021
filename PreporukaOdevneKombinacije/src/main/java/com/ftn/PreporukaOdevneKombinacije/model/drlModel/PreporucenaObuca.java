package com.ftn.PreporukaOdevneKombinacije.model.drlModel;


import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import com.ftn.PreporukaOdevneKombinacije.model.Obuca;

public class PreporucenaObuca {

    private Obuca obuca;
    private Double poeni;

    public PreporucenaObuca(Obuca obuca, Double poeni) {
        this.obuca = obuca;
        this.poeni = poeni;
    }

    public PreporucenaObuca(KomadOdece obuca, Integer poeni) {
        this.obuca = (Obuca) obuca;
        this.poeni = (double) poeni;
    }

    public PreporucenaObuca(Object obuca) {
        this.obuca = (Obuca) obuca;
    }

    public PreporucenaObuca() {
    }

    public Obuca getObuca() {
        return obuca;
    }

    public void setObuca(Obuca obuca) {
        this.obuca = obuca;
    }

    public Double getPoeni() {
        return poeni;
    }

    public void setPoeni(Double poeni) {
        this.poeni = poeni;
    }
}
