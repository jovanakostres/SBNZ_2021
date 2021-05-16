package com.ftn.PreporukaOdevneKombinacije.dto;

import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

public class GornjiDeoUnosDTO {

    private Long id;
    private Boja boja;
    private BojaIntenzitet bojaIntenzitet;
    private Materijal materijal;
    private Vreme vreme;
    private int prioritet;
    private double koeficijentOdabira;
    private String image;
    private User korisnik;
    private DuzinaRukava duzinaRukava;
    private Izrez izrez;
    private OdecaPodTip odecaPodTip;
    private GornjiDeoEnum odecaTip;

    public GornjiDeoUnosDTO(Boja boja, Materijal materijal, Vreme vreme, String image, OdecaPodTip odecaPodTip, GornjiDeoEnum odecaTip) {
        this.boja = boja;
        this.materijal = materijal;
        this.vreme = vreme;
        this.prioritet = 0;
        this.koeficijentOdabira = 1;
        this.image = image;
        this.odecaPodTip = odecaPodTip;
        this.odecaTip = odecaTip;
    }

    public GornjiDeoUnosDTO(){}

    public GornjiDeoUnosDTO(GornjiDeo gornjiDeo){
        this.id = gornjiDeo.getId();
        this.boja = gornjiDeo.getBoja();
        this.bojaIntenzitet = gornjiDeo.getBojaIntenzitet();
        this.materijal = gornjiDeo.getMaterijal();
        this.vreme = gornjiDeo.getVreme();
        this.prioritet = gornjiDeo.getPrioritet();
        this.koeficijentOdabira = gornjiDeo.getKoeficijentOdabira();
        this.image = gornjiDeo.getImage();
        this.duzinaRukava = gornjiDeo.getDuzinaRukava();
        this.izrez = gornjiDeo.getIzrez();
        this.odecaPodTip = gornjiDeo.getOdecaPodTip();
        this.odecaTip = gornjiDeo.getOdecaTip();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boja getBoja() {
        return boja;
    }

    public void setBoja(Boja boja) {
        this.boja = boja;
    }

    public BojaIntenzitet getBojaIntenzitet() {
        return bojaIntenzitet;
    }

    public void setBojaIntenzitet(BojaIntenzitet bojaIntenzitet) {
        this.bojaIntenzitet = bojaIntenzitet;
    }

    public Materijal getMaterijal() {
        return materijal;
    }

    public void setMaterijal(Materijal materijal) {
        this.materijal = materijal;
    }

    public Vreme getVreme() {
        return vreme;
    }

    public void setVreme(Vreme vreme) {
        this.vreme = vreme;
    }

    public int getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(int prioritet) {
        this.prioritet = prioritet;
    }

    public double getKoeficijentOdabira() {
        return koeficijentOdabira;
    }

    public void setKoeficijentOdabira(double koeficijentOdabira) {
        this.koeficijentOdabira = koeficijentOdabira;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(User korisnik) {
        this.korisnik = korisnik;
    }

    public DuzinaRukava getDuzinaRukava() {
        return duzinaRukava;
    }

    public void setDuzinaRukava(DuzinaRukava duzinaRukava) {
        this.duzinaRukava = duzinaRukava;
    }

    public Izrez getIzrez() {
        return izrez;
    }

    public void setIzrez(Izrez izrez) {
        this.izrez = izrez;
    }

    public OdecaPodTip getOdecaPodTip() {
        return odecaPodTip;
    }

    public void setOdecaPodTip(OdecaPodTip odecaPodTip) {
        this.odecaPodTip = odecaPodTip;
    }

    public GornjiDeoEnum getOdecaTip() {
        return odecaTip;
    }

    public void setOdecaTip(GornjiDeoEnum odecaTip) {
        this.odecaTip = odecaTip;
    }
}
