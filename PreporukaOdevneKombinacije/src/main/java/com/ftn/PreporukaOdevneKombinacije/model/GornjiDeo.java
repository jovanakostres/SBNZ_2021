package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

public class GornjiDeo extends KomadOdece{
    private DuzinaRukava duzinaRukava;
    private Izrez izrez;
    private OdecaPodTip odecaPodTip;
    private GornjiDeoEnum odecaTip;

    public GornjiDeo(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, DuzinaRukava duzina, Izrez izrez) {
        super(boja, bojaIntenzitet, materijal, vreme, prioritet);
        this.duzinaRukava = duzina;
        this.izrez = izrez;
    }
}
