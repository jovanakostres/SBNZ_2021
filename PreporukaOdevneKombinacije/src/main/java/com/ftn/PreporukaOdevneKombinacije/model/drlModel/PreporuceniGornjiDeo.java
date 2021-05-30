package com.ftn.PreporukaOdevneKombinacije.model.drlModel;

import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;

public class PreporuceniGornjiDeo {

    private GornjiDeo gornjiDeo;
    private Double poeni;

    public PreporuceniGornjiDeo(GornjiDeo gornjiDeo, Double poeni) {
        this.gornjiDeo = gornjiDeo;
        this.poeni = poeni;
    }

    public PreporuceniGornjiDeo(KomadOdece gornjiDeo, Integer poeni) {
        this.gornjiDeo = (GornjiDeo) gornjiDeo;
        this.poeni = (double)poeni;
    }

    public PreporuceniGornjiDeo(Object gornjiDeo) {
        this.gornjiDeo =  (GornjiDeo) gornjiDeo;
    }

    public GornjiDeo getGornjiDeo() {
        return gornjiDeo;
    }

    public void setGornjiDeo(GornjiDeo gornjiDeo) {
        this.gornjiDeo = gornjiDeo;
    }

    public Double getPoeni() {
        return poeni;
    }

    public void setPoeni(Double poeni) {
        this.poeni = poeni;
    }
}
