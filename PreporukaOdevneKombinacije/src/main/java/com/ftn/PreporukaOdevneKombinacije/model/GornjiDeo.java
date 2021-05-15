package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

import javax.persistence.*;

@Entity
public class GornjiDeo extends KomadOdece{

    @Column(name = "duzr")
    private DuzinaRukava duzinaRukava;

    @Column
    private Izrez izrez;

    @Column(name = "opt")
    private OdecaPodTip odecaPodTip;

    @Column(name = "ot")
    private GornjiDeoEnum odecaTip;

    public GornjiDeo(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, String image, DuzinaRukava duzina, Izrez izrez, OdecaPodTip odecaPodTip, GornjiDeoEnum odecaTip) {
        super(boja, bojaIntenzitet, materijal, vreme, prioritet, image);
        this.duzinaRukava = duzina;
        this.izrez = izrez;
        this.odecaPodTip = odecaPodTip;
        this.odecaTip = odecaTip;
    }
}
