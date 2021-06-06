package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

import javax.persistence.*;

@Entity
public class Jakna extends KomadOdece{

    @Column(name = "ot")
    private JaknaEnum odecaTip;

    @Column(name = "opt")
    private OdecaPodTip odecaPodTip;

    public Jakna(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, String image, JaknaEnum odecaTip, OdecaPodTip odecaPodTip) {
        super(boja, bojaIntenzitet, materijal, vreme, prioritet, image);
        this.odecaTip = odecaTip;
        this.odecaPodTip = odecaPodTip;
    }

    public Jakna(Boja boja, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, boolean aktivan, JaknaEnum odecaTip, OdecaPodTip odecaPodTip) {
        super(boja, materijal, vreme, prioritet, koeficijentOdabira, image, aktivan);
        this.odecaTip = odecaTip;
        this.odecaPodTip = odecaPodTip;
    }

    public Jakna(Long id, Boja boja, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, boolean aktivan, JaknaEnum odecaTip, OdecaPodTip odecaPodTip) {
        super(id, boja, materijal, vreme, prioritet, koeficijentOdabira, image, aktivan);
        this.odecaTip = odecaTip;
        this.odecaPodTip = odecaPodTip;
    }

    public Jakna(JaknaEnum odecaTip, OdecaPodTip odecaPodTip) {
        this.odecaTip = odecaTip;
        this.odecaPodTip = odecaPodTip;
    }

    public Jakna(Long id, Boja boja, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, User korisnik, JaknaEnum odecaTip, OdecaPodTip odecaPodTip) {
        super(id, boja, materijal, vreme, prioritet, koeficijentOdabira, image, korisnik);
        this.odecaTip = odecaTip;
        this.odecaPodTip = odecaPodTip;
    }

    public Jakna(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, String image, JaknaEnum odecaTip, OdecaPodTip odecaPodTip, Pol pol) {
        super(boja, bojaIntenzitet, materijal, vreme, prioritet, image, pol);
        this.odecaTip = odecaTip;
        this.odecaPodTip = odecaPodTip;
    }

    public Jakna(){}

    public JaknaEnum getOdecaTip() {
        return odecaTip;
    }

    public void setOdecaTip(JaknaEnum odecaTip) {
        this.odecaTip = odecaTip;
    }

    public OdecaPodTip getOdecaPodTip() {
        return odecaPodTip;
    }

    public void setOdecaPodTip(OdecaPodTip odecaPodTip) {
        this.odecaPodTip = odecaPodTip;
    }
}
