package com.ftn.PreporukaOdevneKombinacije.model.event;

import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("7d")
public class UcestalostKomada {

    private KomadOdece komadOdece;
    private Integer brojPonavljanja;

    public UcestalostKomada(KomadOdece komadOdece, Integer brojPonavljanja) {

        this.komadOdece = komadOdece;
        this.brojPonavljanja = brojPonavljanja;
    }

    public UcestalostKomada() {
    }

    public KomadOdece getKomadOdece() {
        return komadOdece;
    }

    public void setKomadOdece(KomadOdece komadOdece) {
        this.komadOdece = komadOdece;
    }

    public Integer getBrojPonavljanja() {
        return brojPonavljanja;
    }

    public void setBrojPonavljanja(Integer brojPonavljanja) {
        this.brojPonavljanja = brojPonavljanja;
    }
}
