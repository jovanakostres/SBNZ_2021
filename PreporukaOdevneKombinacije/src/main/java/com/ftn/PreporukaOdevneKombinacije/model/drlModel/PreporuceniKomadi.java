package com.ftn.PreporukaOdevneKombinacije.model.drlModel;

import com.ftn.PreporukaOdevneKombinacije.model.*;

import java.util.ArrayList;
import java.util.List;

public class PreporuceniKomadi {

    private List<PreporuceniGornjiDeo> preporuceniGornjiDelovi;
    private List<PreporuceniDonjiDeo> preporuceniDonjiDelovi;
    private List<Jakna> preporuceneJakne;
    private List<Obuca> preporucenaObuca;

    public PreporuceniKomadi(List<PreporuceniGornjiDeo> preporuceniGornjiDelovi, List<PreporuceniDonjiDeo> preporuceniDonjiDelovi, List<Jakna> preporuceneJakne, List<Obuca> preporucenaObuca) {
        this.preporuceniGornjiDelovi = preporuceniGornjiDelovi;
        this.preporuceniDonjiDelovi = preporuceniDonjiDelovi;
        this.preporuceneJakne = preporuceneJakne;
        this.preporucenaObuca = preporucenaObuca;
    }

    public PreporuceniKomadi(){
        this.preporuceniGornjiDelovi = new ArrayList<>();
        this.preporuceniDonjiDelovi = new ArrayList<>();
    }

    public List<PreporuceniGornjiDeo> getPreporuceniGornjiDelovi() {
        return preporuceniGornjiDelovi;
    }

    public void setPreporuceniGornjiDelovi(List<PreporuceniGornjiDeo> preporuceniGornjiDelovi) {
        this.preporuceniGornjiDelovi = preporuceniGornjiDelovi;
    }

    public List<PreporuceniDonjiDeo> getPreporuceniDonjiDelovi() {
        return preporuceniDonjiDelovi;
    }

    public void setPreporuceniDonjiDelovi(List<PreporuceniDonjiDeo> preporuceniDonjiDelovi) {
        this.preporuceniDonjiDelovi = preporuceniDonjiDelovi;
    }

    public List<Jakna> getPreporuceneJakne() {
        return preporuceneJakne;
    }

    public void setPreporuceneJakne(List<Jakna> preporuceneJakne) {
        this.preporuceneJakne = preporuceneJakne;
    }

    public List<Obuca> getPreporucenaObuca() {
        return preporucenaObuca;
    }

    public void setPreporucenaObuca(List<Obuca> preporucenaObuca) {
        this.preporucenaObuca = preporucenaObuca;
    }
}
