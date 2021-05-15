package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

import javax.persistence.*;

@Entity
public class Jakna extends KomadOdece{

    @Column
    private JaknaTip tipJakne;

    public Jakna(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, String image, JaknaTip tip) {
        super(boja, bojaIntenzitet, materijal, vreme, prioritet, image);
        this.tipJakne = tip;
    }
}
