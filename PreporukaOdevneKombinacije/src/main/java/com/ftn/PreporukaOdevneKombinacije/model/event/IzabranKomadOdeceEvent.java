package com.ftn.PreporukaOdevneKombinacije.model.event;


import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("7d")
public class IzabranKomadOdeceEvent {

    private KomadOdece komadOdece;

    private String satnica;

    public IzabranKomadOdeceEvent(KomadOdece gornjiDeo) {
        this.komadOdece = gornjiDeo;
    }

    public IzabranKomadOdeceEvent(KomadOdece komadOdece, String satnica) {
        this.komadOdece = komadOdece;
        this.satnica = satnica;
    }

    public IzabranKomadOdeceEvent(){}

    public KomadOdece getKomadOdece() {
        return komadOdece;
    }

    public void setKomadOdece(KomadOdece komadOdece) {
        this.komadOdece = komadOdece;
    }

    public String getSatnica() {
        return satnica;
    }

    public void setSatnica(String satnica) {
        this.satnica = satnica;
    }
}
