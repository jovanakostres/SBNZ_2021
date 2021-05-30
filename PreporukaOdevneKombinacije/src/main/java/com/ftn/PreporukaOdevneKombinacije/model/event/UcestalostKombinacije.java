package com.ftn.PreporukaOdevneKombinacije.model.event;

import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;


@Role(Role.Type.EVENT)
@Expires("7d")
public class UcestalostKombinacije {
    private KomadOdece komadOdece1;
    private KomadOdece komadOdece2;
    private Integer brojPonavljanja;

    public UcestalostKombinacije(KomadOdece komadOdece1, KomadOdece komadOdece2, Integer brojPonavljanja) {
        this.komadOdece1 = komadOdece1;
        this.komadOdece2 = komadOdece2;
        this.brojPonavljanja = brojPonavljanja;
    }

    public UcestalostKombinacije(KomadOdece komadOdece1, KomadOdece komadOdece2) {
        this.komadOdece1 = komadOdece1;
        this.komadOdece2 = komadOdece2;
    }

    public UcestalostKombinacije() {
    }

    public Integer getBrojPonavljanja() {
        return brojPonavljanja;
    }

    public void setBrojPonavljanja(Integer brojPonavljanja) {
        this.brojPonavljanja = brojPonavljanja;
    }

    public KomadOdece getKomadOdece1() {
        return komadOdece1;
    }

    public void setKomadOdece1(KomadOdece komadOdece1) {
        this.komadOdece1 = komadOdece1;
    }

    public KomadOdece getKomadOdece2() {
        return komadOdece2;
    }

    public void setKomadOdece2(KomadOdece komadOdece2) {
        this.komadOdece2 = komadOdece2;
    }
}
