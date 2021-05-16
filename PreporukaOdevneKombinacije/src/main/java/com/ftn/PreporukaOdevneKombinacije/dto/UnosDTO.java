package com.ftn.PreporukaOdevneKombinacije.dto;

import com.ftn.PreporukaOdevneKombinacije.model.enums.Boja;
import com.ftn.PreporukaOdevneKombinacije.model.enums.DressCode;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Vreme;

import java.util.List;

public class UnosDTO {
    private double temperatura;
    private Vreme vreme;
    private String mesto;
    private DressCode dressCode;
    private List<Boja> boje;

    public UnosDTO(double temperatura, Vreme vreme, String mesto, DressCode dressCode, List<Boja> boje) {
        this.temperatura = temperatura;
        this.vreme = vreme;
        this.mesto = mesto;
        this.dressCode = dressCode;
        this.boje = boje;
    }

    public UnosDTO(String mesto, DressCode dressCode, List<Boja> boje) {
        this.mesto = mesto;
        this.dressCode = dressCode;
        this.boje = boje;
    }

    public UnosDTO() {}

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public Vreme getVreme() {
        return vreme;
    }

    public void setVreme(Vreme vreme) {
        this.vreme = vreme;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public DressCode getDressCode() {
        return dressCode;
    }

    public void setDressCode(DressCode dressCode) {
        this.dressCode = dressCode;
    }

    public List<Boja> getBoje() {
        return boje;
    }

    public void setBoje(List<Boja> boje) {
        this.boje = boje;
    }
}
