package com.ftn.PreporukaOdevneKombinacije.service;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniGornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.*;
import com.ftn.PreporukaOdevneKombinacije.repository.GornjiDeoRepository;
import com.ftn.PreporukaOdevneKombinacije.repository.KomadOdeceRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GornjiDeoService {

    @Autowired
    private GornjiDeoRepository repository;

    @Autowired
    private KieContainer kieContainer;

    public GornjiDeo create(GornjiDeo komadOdece){
        return repository.save(komadOdece);
    }

    public PreporuceniKomadi getPreporuceniGornjiDeo(UnosDTO unosDTO, User user, List<GornjiDeo> komadi, PreporuceniKomadi preporuceniKomadi) {
        KieSession kieSession = kieContainer.newKieSession("gDPersRulesSession");
        for(GornjiDeo komadOdece : komadi){
            kieSession.insert(komadOdece);
        }

        kieSession.setGlobal("hashMapColor", makeHashMapColor());

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        kieSession.insert(preporuceniKomadi);
        kieSession.insert(unosDTO);
        kieSession.insert(user);
        kieSession.fireAllRules();

        System.out.println(preporuceniKomadi.getPreporuceniGornjiDelovi().size());

        return preporuceniKomadi;
    }

    public void insertTipTela(KieSession kieSession){
        kieSession.insert(TipTela.TROUGAO);
        kieSession.insert(TipTela.OB_TROUGAO);
        kieSession.insert(TipTela.KRUG);
        kieSession.insert(TipTela.PES_SAT);
        kieSession.insert(TipTela.PRAVOUGAONIK);
    }

    public void insertPodTip(KieSession kieSession){
        kieSession.insert(OdecaPodTip.SIROKA);
        kieSession.insert(OdecaPodTip.USKA);
    }

    public void insertVreme(KieSession kieSession){
        kieSession.insert(Vreme.SUVO);
        kieSession.insert(Vreme.VLAZNO);
    }

    public void insertDresscode(KieSession kieSession){
        kieSession.insert(DressCode.LEZERAN);
        kieSession.insert(DressCode.BLACKTIE);
        kieSession.insert(DressCode.FORMALAN);
        kieSession.insert(DressCode.IZLAZAK);
        kieSession.insert(DressCode.ODMOR);
        kieSession.insert(DressCode.POSLOVNI);
        kieSession.insert(DressCode.SPORTSKI);
    }

    public void insertOdecaTip(KieSession kieSession){
        kieSession.insert(GornjiDeoEnum.BLUZA);
        kieSession.insert(GornjiDeoEnum.DUKS);
        kieSession.insert(GornjiDeoEnum.DZEMPER);
        kieSession.insert(GornjiDeoEnum.DZEMPER_RASKOPCAVANJE);
        kieSession.insert(GornjiDeoEnum.KARDIGAN);
        kieSession.insert(GornjiDeoEnum.KOSULJA);
        kieSession.insert(GornjiDeoEnum.MAJICA);
        kieSession.insert(GornjiDeoEnum.MAJICA_BRETELE);
        kieSession.insert(GornjiDeoEnum.MAJICA_KRATKI);
        kieSession.insert(GornjiDeoEnum.MAJICA_DUGI);
    }

    public void insertMaterijal(KieSession kieSession){
        kieSession.insert(Materijal.KRZNO);
        kieSession.insert(Materijal.CIPKA);
        kieSession.insert(Materijal.KRZNO);
        kieSession.insert(Materijal.KOZA);
        kieSession.insert(Materijal.LAN);
        kieSession.insert(Materijal.PAMUK);
        kieSession.insert(Materijal.PLIS);
        kieSession.insert(Materijal.POLIESTER);
        kieSession.insert(Materijal.SOMOT);
        kieSession.insert(Materijal.SPANDEX);
        kieSession.insert(Materijal.SVILA);
        kieSession.insert(Materijal.TEKSAS);
        kieSession.insert(Materijal.VUNA);
    }

    public HashMap<DressCode, Integer> makeHashMapColor(){
        HashMap<DressCode, Integer> map = new HashMap<>();
        map.put(DressCode.LEZERAN, 250);
        map.put(DressCode.BLACKTIE, 300);
        return map;
    }
}
