package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

public class Obuca extends KomadOdece{

    private Vodootpornost vodootpornost;
    private ObucaTip obucaTip;
    private Stikla stikla;

    public Obuca(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, Vodootpornost vodootpornost, ObucaTip obucaTip, Stikla stikla) {
        super(boja, bojaIntenzitet, materijal, vreme, prioritet);
        this.vodootpornost = vodootpornost;
        this.obucaTip = obucaTip;
        this.stikla = stikla;
    }

}
