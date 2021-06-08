package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

import javax.persistence.*;

@Entity
public class Obuca extends KomadOdece{

    @Column(name = "ot")
    private ObucaEnum obucaTip;

    @Column
    private Stikla stikla;

    public Obuca(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, String image, ObucaEnum obucaTip, Stikla stikla) {
        super(boja, bojaIntenzitet, materijal, vreme, prioritet, image);
        this.obucaTip = obucaTip;
        this.stikla = stikla;
    }

    public Obuca(Boja boja, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, boolean aktivan, ObucaEnum obucaTip) {
        super(boja, materijal, vreme, prioritet, koeficijentOdabira, image, aktivan);
        this.obucaTip = obucaTip;
    }

    public Obuca(Long id, Boja boja, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, boolean aktivan, ObucaEnum obucaTip) {
        super(id, boja, materijal, vreme, prioritet, koeficijentOdabira, image, aktivan);
        this.obucaTip = obucaTip;
    }

    public Obuca(ObucaEnum obucaTip, Stikla stikla) {
        this.obucaTip = obucaTip;
        this.stikla = stikla;
    }

    public Obuca(Long id, Boja boja, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, User korisnik, boolean aktivan, ObucaEnum obucaTip) {
        super(id, boja, materijal, vreme, prioritet, koeficijentOdabira, image, korisnik, aktivan);
        this.obucaTip = obucaTip;
    }

    public Obuca(){}

    public Obuca(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, Pol pol, ObucaEnum obucaTip, Stikla stikla) {
        super(boja,bojaIntenzitet, materijal, vreme, prioritet, koeficijentOdabira, image, pol);
        this.obucaTip = obucaTip;
        this.stikla = stikla;
    }

    public ObucaEnum getObucaTip() {
        return obucaTip;
    }

    public void setObucaTip(ObucaEnum obucaTip) {
        this.obucaTip = obucaTip;
    }

    public Stikla getStikla() {
        return stikla;
    }

    public void setStikla(Stikla stikla) {
        this.stikla = stikla;
    }
}
