package com.ftn.PreporukaOdevneKombinacije.helper;

import com.ftn.PreporukaOdevneKombinacije.dto.OdecaAddAdminDTO;
import com.ftn.PreporukaOdevneKombinacije.model.*;
import com.ftn.PreporukaOdevneKombinacije.model.enums.ObucaEnum;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Stikla;

public class KomadiMapper implements MapperInterface<KomadOdece, OdecaAddAdminDTO>{
    @Override
    public KomadOdece toEntity(OdecaAddAdminDTO dto) {
        if(dto.getTip().equalsIgnoreCase("gornji_deo"))
            return new GornjiDeo(dto.getBoja(), dto.getBojaIntenzitet(), dto.getMaterijal(), dto.getVreme(), dto.getPrioritet(), dto.getKoeficijentOdabira(), dto.getImage(), dto.getIzrez(), dto.getOdecaPodTip(), dto.getOdecaTip(), dto.getDuzinaRukava(), dto.getPol());
        if(dto.getTip().equalsIgnoreCase("donji_deo"))
            return new DonjiDeo(dto.getBoja(), dto.getBojaIntenzitet(), dto.getMaterijal(), dto.getVreme(), dto.getPrioritet(), dto.getKoeficijentOdabira(), dto.getImage(), dto.getDuzina(), dto.getDubina(), dto.getOdecaPodTip(), dto.getOdecadTip(), dto.getTipDonjegDela(), dto.getPol());
        if(dto.getTip().equalsIgnoreCase("jakna"))
            return new Jakna(dto.getBoja(), dto.getBojaIntenzitet(), dto.getMaterijal(), dto.getVreme(), dto.getPrioritet(), dto.getKoeficijentOdabira(), dto.getImage(), dto.getPol(), dto.getOdecajTip(), dto.getOdecaPodTip());
        return new Obuca(dto.getBoja(), dto.getBojaIntenzitet(), dto.getMaterijal(), dto.getVreme(), dto.getPrioritet(), dto.getKoeficijentOdabira(), dto.getImage(), dto.getPol(), dto.getObucaTip(), dto.getStikla());
    }


    public KomadOdece toEntityUser(OdecaAddAdminDTO dto) {
        if(dto.getTip().equalsIgnoreCase("GORNJIDEO"))
            return new GornjiDeo(dto.getBoja(), dto.getMaterijal(), dto.getVreme(), 0,1, dto.getImage(), true, dto.getOdecaPodTip(), dto.getOdecaTip());
        if(dto.getTip().equalsIgnoreCase("DONJIDEO"))
            return new DonjiDeo(dto.getBoja(), dto.getMaterijal(), dto.getVreme(), 0, 1, dto.getImage(), true, dto.getOdecadTip(), dto.getOdecaPodTip());
        if(dto.getTip().equalsIgnoreCase("JAKNA"))
            return new Jakna(dto.getBoja(), dto.getMaterijal(), dto.getVreme(), 0, 1, dto.getImage(), true, dto.getOdecajTip(), dto.getOdecaPodTip());
        return new Obuca(dto.getBoja(), dto.getMaterijal(), dto.getVreme(), 0, 1, dto.getImage(), true, dto.getObucaTip());
    }

    @Override
    public OdecaAddAdminDTO toDto(KomadOdece entity) {
        if(entity.getClass() == GornjiDeo.class)
            return new OdecaAddAdminDTO(entity.getBoja(), entity.getBojaIntenzitet(), entity.getMaterijal(), entity.getVreme(), entity.getPrioritet(), entity.getKoeficijentOdabira(), entity.getImage(), entity.isAktivan(), entity.getPol(), ((GornjiDeo) entity).getDuzinaRukava(), ((GornjiDeo) entity).getIzrez(), ((GornjiDeo) entity).getOdecaTip(), "gornji_deo");
        if(entity.getClass() == DonjiDeo.class)
            return new OdecaAddAdminDTO(entity.getBoja(), entity.getBojaIntenzitet(), entity.getMaterijal(), entity.getVreme(), entity.getPrioritet(), entity.getKoeficijentOdabira(), entity.getImage(), entity.isAktivan(), entity.getPol(), ((DonjiDeo) entity).getOdecaTip(), ((DonjiDeo) entity).getDubina(), ((DonjiDeo) entity).getDuzina(), ((DonjiDeo) entity).getTipDonjegDela(), "donji_deo");
        if(entity.getClass() == Jakna.class)
            return new OdecaAddAdminDTO(entity.getBoja(), entity.getBojaIntenzitet(), entity.getMaterijal(), entity.getVreme(), entity.getPrioritet(), entity.getKoeficijentOdabira(), entity.getImage(), entity.isAktivan(), entity.getPol(), ((Jakna) entity).getOdecaTip(), "jakna");
        return new OdecaAddAdminDTO(entity.getBoja(), entity.getBojaIntenzitet(), entity.getMaterijal(), entity.getVreme(), entity.getPrioritet(), entity.getKoeficijentOdabira(), entity.getImage(), entity.isAktivan(), entity.getPol(), ((Obuca) entity).getObucaTip(), ((Obuca) entity).getStikla(), "obuca");
    }
}
