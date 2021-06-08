package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

import javax.persistence.*;

@Entity
public class DonjiDeo extends KomadOdece {

    @Column(name = "ot")
    private DonjiDeoEnum odecaTip;

    @Column(name = "opt")
    private OdecaPodTip odecaPodTip;

    @Column
    private Dubina dubina;

    @Column
    private DuzinaDonjegDela duzina;

    @Column(name = "tdd")
    private TipDonjegDela tipDonjegDela;

    public DonjiDeo(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, String image, DonjiDeoEnum tip, OdecaPodTip odecaPodTip, Dubina dubina, DuzinaDonjegDela duzina, TipDonjegDela tipDonjegDela, Pol pol) {
        super(boja, bojaIntenzitet, materijal, vreme, prioritet, image, pol);
        this.odecaTip = tip;
        this.odecaPodTip = odecaPodTip;
        this.dubina = dubina;
        this.duzina = duzina;
        this.tipDonjegDela = tipDonjegDela;
    }

    public DonjiDeo(Boja boja, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, boolean aktivan, DonjiDeoEnum tip, OdecaPodTip odecaPodTip) {
        super(boja, materijal, vreme, prioritet, koeficijentOdabira, image, aktivan);
        this.odecaTip = tip;
        this.odecaPodTip = odecaPodTip;
    }

    public DonjiDeo(Long id, Boja boja, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, boolean aktivan, DonjiDeoEnum tip, OdecaPodTip odecaPodTip) {
        super(id, boja, materijal, vreme, prioritet, koeficijentOdabira, image, aktivan);
        this.odecaTip = tip;
        this.odecaPodTip = odecaPodTip;
    }

    public DonjiDeo(DonjiDeoEnum tip, OdecaPodTip odecaPodTip, Dubina dubina, DuzinaDonjegDela duzina) {
        this.odecaTip = tip;
        this.odecaPodTip = odecaPodTip;
        this.dubina = dubina;
        this.duzina = duzina;
    }

    public DonjiDeo(Long id, Boja boja, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, User korisnik, boolean aktivan, DonjiDeoEnum odecaTip, OdecaPodTip odecaPodTip) {
        super(id, boja, materijal, vreme, prioritet, koeficijentOdabira, image, korisnik, aktivan);
        this.odecaTip = odecaTip;
        this.odecaPodTip = odecaPodTip;
    }

    public DonjiDeo(){}

    public DonjiDeoEnum getOdecaTip() {
        return odecaTip;
    }

    public void setOdecaTip(DonjiDeoEnum tip) {
        this.odecaTip = tip;
    }

    public OdecaPodTip getOdecaPodTip() {
        return odecaPodTip;
    }

    public void setOdecaPodTip(OdecaPodTip odecaPodTip) {
        this.odecaPodTip = odecaPodTip;
    }

    public Dubina getDubina() {
        return dubina;
    }

    public void setDubina(Dubina dubina) {
        this.dubina = dubina;
    }

    public DuzinaDonjegDela getDuzina() {
        return duzina;
    }

    public void setDuzina(DuzinaDonjegDela duzina) {
        this.duzina = duzina;
    }

    public TipDonjegDela getTipDonjegDela() { return tipDonjegDela; }

    public void setTipDonjegDela(TipDonjegDela tipDonjegDela) { this.tipDonjegDela = tipDonjegDela; }
}
