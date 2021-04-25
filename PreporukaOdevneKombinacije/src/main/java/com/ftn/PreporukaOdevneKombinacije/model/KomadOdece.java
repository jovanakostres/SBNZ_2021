package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

public abstract class KomadOdece {
    private Long id;
    private Boja boja;
    private BojaIntenzitet bojaIntenzitet;
    private Materijal materijal;
    private Vreme vreme;
    private int prioritet;
    private double koeficijentOdabira;

    public KomadOdece(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet) {
        this.boja = boja;
        this.bojaIntenzitet = bojaIntenzitet;
        this.materijal = materijal;
        this.vreme = vreme;
        this.prioritet = prioritet;
    }

}
