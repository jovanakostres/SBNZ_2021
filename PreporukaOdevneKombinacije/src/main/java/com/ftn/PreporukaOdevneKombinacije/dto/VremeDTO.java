package com.ftn.PreporukaOdevneKombinacije.dto;

import java.util.ArrayList;

public class VremeDTO {

    private ArrayList<String> listaVremena;

    public VremeDTO(ArrayList<String> listaVremena) {
        this.listaVremena = listaVremena;
    }

    public VremeDTO() {
        listaVremena = new ArrayList<>();
    }

    public ArrayList<String> getListaVremena() {
        return listaVremena;
    }

    public void setListaVremena(ArrayList<String> listaVremena) {
        this.listaVremena = listaVremena;
    }
}
