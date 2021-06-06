package com.ftn.PreporukaOdevneKombinacije.model.event;

import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("5d")
public class OdbijenKomadEvent {

    private KomadOdece komadOdece;

    public OdbijenKomadEvent(KomadOdece gornjiDeo) {
        this.komadOdece = gornjiDeo;
    }

    public OdbijenKomadEvent(){}

    public KomadOdece getKomadOdece() {
        return komadOdece;
    }

    public void setKomadOdece(KomadOdece komadOdece) {
        this.komadOdece = komadOdece;
    }

}
