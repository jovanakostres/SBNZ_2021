package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

public class DonjiDeo extends KomadOdece {
    private DonjiDeoEnum tip;
    private Dubina dubina;
    private DuzinaDonjegDela duzina;

    public DonjiDeo(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, DonjiDeoEnum tip, Dubina dubina, DuzinaDonjegDela duzina) {
        super(boja, bojaIntenzitet, materijal,vreme, prioritet);
        this.tip = tip;
        this.dubina = dubina;
        this.duzina = duzina;
    }
}
