package com.ftn.PreporukaOdevneKombinacije.helper;

import com.ftn.PreporukaOdevneKombinacije.dto.GornjiDeoUnosDTO;
import com.ftn.PreporukaOdevneKombinacije.dto.PreporuceniKomadiDTO;
import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Boja;
import com.ftn.PreporukaOdevneKombinacije.model.enums.GornjiDeoEnum;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Materijal;
import com.ftn.PreporukaOdevneKombinacije.model.enums.OdecaPodTip;

public class GornjiDeoMapper implements MapperInterface<GornjiDeo, GornjiDeoUnosDTO>{
    @Override
    public GornjiDeo toEntity(GornjiDeoUnosDTO dto) {
        return new GornjiDeo(dto.getBoja(), dto.getMaterijal(), dto.getPrioritet(), dto.getVreme(), dto.getKoeficijentOdabira(), dto.getImage(),
                dto.getOdecaPodTip(), dto.getOdecaTip());
    }

    @Override
    public GornjiDeoUnosDTO toDto(GornjiDeo entity) {
        return null;
    }
}
