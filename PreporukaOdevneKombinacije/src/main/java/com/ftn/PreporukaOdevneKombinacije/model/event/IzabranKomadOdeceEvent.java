package com.ftn.PreporukaOdevneKombinacije.model.event;


import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("7d")
public class IzabranKomadOdeceEvent {

    private KomadOdece komadOdece;

    public IzabranKomadOdeceEvent(KomadOdece gornjiDeo) {
        this.komadOdece = gornjiDeo;
    }

    public IzabranKomadOdeceEvent(){}

    public KomadOdece getKomadOdece() {
        return komadOdece;
    }

    public void setKomadOdece(KomadOdece komadOdece) {
        this.komadOdece = komadOdece;
    }
}
