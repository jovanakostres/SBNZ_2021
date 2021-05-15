package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

import javax.persistence.*;

@Entity
public class Obuca extends KomadOdece{

    @Column
    private Vodootpornost vodootpornost;

    @Column
    private ObucaTip obucaTip;

    @Column
    private Stikla stikla;

    public Obuca(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, String image, Vodootpornost vodootpornost, ObucaTip obucaTip, Stikla stikla) {
        super(boja, bojaIntenzitet, materijal, vreme, prioritet, image);
        this.vodootpornost = vodootpornost;
        this.obucaTip = obucaTip;
        this.stikla = stikla;
    }

}
