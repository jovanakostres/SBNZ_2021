package com.ftn.PreporukaOdevneKombinacije.model.event;

import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("14d")
public class DeaktiviranKomadEvent {

    private KomadOdece komad;

    public DeaktiviranKomadEvent() {
    }

    public DeaktiviranKomadEvent(KomadOdece komad) {
        this.komad = komad;
    }

    public KomadOdece getKomad() {
        return komad;
    }

    public void setKomad(KomadOdece komad) {
        this.komad = komad;
    }
}
