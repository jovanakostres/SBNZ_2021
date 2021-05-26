package com.ftn.PreporukaOdevneKombinacije.model.drlModel;

import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;

public class KoeficijentPreporuke {

    private Double bodovi;
    private KomadOdece komad;


    public KoeficijentPreporuke(Double bodovi, KomadOdece komad) {
        this.bodovi = bodovi;
        this.komad = komad;
    }

    public KoeficijentPreporuke() {

    }

    public KoeficijentPreporuke(Long bodovi, KomadOdece komad) {
        this.bodovi = bodovi.doubleValue();
        this.komad = komad;
    }

    public KoeficijentPreporuke(int bodovi, KomadOdece komad) {
        this.bodovi = (double) bodovi;
        this.komad = komad;
    }

    public Double getBodovi() {
        return bodovi;
    }

    public void setBodovi(Double bodovi) {
        this.bodovi = bodovi;
    }

    public KomadOdece getKomad() {
        return komad;
    }

    public void setKomad(KomadOdece komad) {
        this.komad = komad;
    }
}


