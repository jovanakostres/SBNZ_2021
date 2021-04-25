package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

public class Jakna extends KomadOdece{
    private JaknaTip tipJakne;

    public Jakna(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, JaknaTip tip) {
        super(boja, bojaIntenzitet, materijal, vreme, prioritet);
        this.tipJakne = tip;
    }
}
