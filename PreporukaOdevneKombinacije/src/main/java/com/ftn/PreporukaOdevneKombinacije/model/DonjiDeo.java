package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

import javax.persistence.*;

@Entity
public class DonjiDeo extends KomadOdece {

    @Column
    private DonjiDeoEnum tip;

    @Column
    private Dubina dubina;

    @Column
    private DuzinaDonjegDela duzina;

    public DonjiDeo(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, String image, DonjiDeoEnum tip, Dubina dubina, DuzinaDonjegDela duzina) {
        super(boja, bojaIntenzitet, materijal,vreme, prioritet, image);
        this.tip = tip;
        this.dubina = dubina;
        this.duzina = duzina;
    }
}
