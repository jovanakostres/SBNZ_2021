package com.ftn.PreporukaOdevneKombinacije.dto;

import com.ftn.PreporukaOdevneKombinacije.model.enums.BojaKoze;
import com.ftn.PreporukaOdevneKombinacije.model.enums.DressCode;

public class UnosNeulogovanDTO {
    private int ramena, kukovi, struk;
    private int visina;
    private DressCode dressCode;
    private BojaKoze bojaKoze;
    private String vreme;

    public UnosNeulogovanDTO(){}

    public UnosNeulogovanDTO(int ramena, int kukovi, int struk, int visina, DressCode dressCode, BojaKoze bojaKoze, String vreme) {
        this.ramena = ramena;
        this.kukovi = kukovi;
        this.struk = struk;
        this.visina = visina;
        this.dressCode = dressCode;
        this.bojaKoze = bojaKoze;
        this.vreme = vreme;
    }

    public int getRamena() {
        return ramena;
    }

    public void setRamena(int ramena) {
        this.ramena = ramena;
    }

    public int getKukovi() {
        return kukovi;
    }

    public void setKukovi(int kukovi) {
        this.kukovi = kukovi;
    }

    public int getStruk() {
        return struk;
    }

    public void setStruk(int struk) {
        this.struk = struk;
    }

    public int getVisina() {
        return visina;
    }

    public void setVisina(int visina) {
        this.visina = visina;
    }

    public DressCode getDressCode() {
        return dressCode;
    }

    public void setDressCode(DressCode dressCode) {
        this.dressCode = dressCode;
    }

    public BojaKoze getBojaKoze() {
        return bojaKoze;
    }

    public void setBojaKoze(BojaKoze bojaKoze) {
        this.bojaKoze = bojaKoze;
    }

    public String getVreme() { return vreme; }

    public void setVreme(String vreme) { this.vreme = vreme; }
}
