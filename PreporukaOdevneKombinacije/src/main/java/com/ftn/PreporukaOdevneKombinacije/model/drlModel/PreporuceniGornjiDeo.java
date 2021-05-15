package com.ftn.PreporukaOdevneKombinacije.model.drlModel;

import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;

public class PreporuceniGornjiDeo {

    private GornjiDeo gornjiDeo;
    private Double poeni;

    public PreporuceniGornjiDeo(GornjiDeo gornjiDeo, Double poeni) {
        this.gornjiDeo = gornjiDeo;
        this.poeni = poeni;
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
