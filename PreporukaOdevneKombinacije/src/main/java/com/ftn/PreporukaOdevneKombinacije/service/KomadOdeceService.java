package com.ftn.PreporukaOdevneKombinacije.service;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KomadOdeceService {

    public PreporuceniKomadi getPreporuceniGornjiDeo(UnosDTO unosDTO, User user) {
        PreporuceniKomadi preporuceniKomadi1 = new PreporuceniKomadi();

        return preporuceniKomadi1;
    }

}
