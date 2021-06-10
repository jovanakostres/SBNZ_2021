package com.ftn.PreporukaOdevneKombinacije.dto;

import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.enums.*;

import javax.persistence.*;

public class OdecaAddAdminDTO {

    private Boja boja;

    private BojaIntenzitet bojaIntenzitet;

    private Materijal materijal;

    private Vreme vreme;

    private int prioritet;

    private double koeficijentOdabira;

    private String image;

    private boolean aktivan;

    private Pol pol;

    private DuzinaRukava duzinaRukava;

    private Izrez izrez;

    private OdecaPodTip odecaPodTip;

    private GornjiDeoEnum odecaTip;

    private DonjiDeoEnum odecadTip;

    private Dubina dubina;

    private DuzinaDonjegDela duzina;

    private TipDonjegDela tipDonjegDela;

    private JaknaEnum odecajTip;

    private ObucaEnum obucaTip;

    private Stikla stikla;

    private String tip;

    public OdecaAddAdminDTO(){}

    public OdecaAddAdminDTO(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, boolean aktivan, Pol pol, DuzinaRukava duzinaRukava, Izrez izrez, GornjiDeoEnum odecaTip, String tip) {
        this.boja = boja;
        this.bojaIntenzitet = bojaIntenzitet;
        this.materijal = materijal;
        this.vreme = vreme;
        this.prioritet = prioritet;
        this.koeficijentOdabira = koeficijentOdabira;
        this.image = image;
        this.aktivan = true;
        this.pol = pol;
        this.duzinaRukava = duzinaRukava;
        this.izrez = izrez;
        this.odecaTip = odecaTip;
        this.tip = tip;
    }

    public OdecaAddAdminDTO(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, boolean aktivan, Pol pol, DonjiDeoEnum odecadTip, Dubina dubina, DuzinaDonjegDela duzina, TipDonjegDela tipDonjegDela, String tip) {
        this.boja = boja;
        this.bojaIntenzitet = bojaIntenzitet;
        this.materijal = materijal;
        this.vreme = vreme;
        this.prioritet = prioritet;
        this.koeficijentOdabira = koeficijentOdabira;
        this.image = image;
        this.aktivan = true;
        this.pol = pol;
        this.odecadTip = odecadTip;
        this.dubina = dubina;
        this.duzina = duzina;
        this.tipDonjegDela = tipDonjegDela;
        this.tip = tip;
    }

    public OdecaAddAdminDTO(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, boolean aktivan, Pol pol, JaknaEnum odecajTip, String tip) {
        this.boja = boja;
        this.bojaIntenzitet = bojaIntenzitet;
        this.materijal = materijal;
        this.vreme = vreme;
        this.prioritet = prioritet;
        this.koeficijentOdabira = koeficijentOdabira;
        this.image = image;
        this.aktivan = true;
        this.pol = pol;
        this.odecajTip = odecajTip;
        this.tip = tip;
    }

    public OdecaAddAdminDTO(Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, boolean aktivan, Pol pol, ObucaEnum obucaTip, Stikla stikla, String tip) {
        this.boja = boja;
        this.bojaIntenzitet = bojaIntenzitet;
        this.materijal = materijal;
        this.vreme = vreme;
        this.prioritet = prioritet;
        this.koeficijentOdabira = koeficijentOdabira;
        this.image = image;
        this.aktivan = true;
        this.pol = pol;
        this.obucaTip = obucaTip;
        this.stikla = stikla;
        this.tip = tip;
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

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
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

    public DonjiDeoEnum getOdecadTip() {
        return odecadTip;
    }

    public void setOdecadTip(DonjiDeoEnum odecadTip) {
        this.odecadTip = odecadTip;
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

    public TipDonjegDela getTipDonjegDela() {
        return tipDonjegDela;
    }

    public void setTipDonjegDela(TipDonjegDela tipDonjegDela) {
        this.tipDonjegDela = tipDonjegDela;
    }

    public JaknaEnum getOdecajTip() {
        return odecajTip;
    }

    public void setOdecajTip(JaknaEnum odecajTip) {
        this.odecajTip = odecajTip;
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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
