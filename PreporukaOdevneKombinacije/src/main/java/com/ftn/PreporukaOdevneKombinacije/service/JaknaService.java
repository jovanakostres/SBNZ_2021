package com.ftn.PreporukaOdevneKombinacije.service;


import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.model.DonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.Jakna;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.*;
import com.ftn.PreporukaOdevneKombinacije.repository.DonjiDeoRepository;
import com.ftn.PreporukaOdevneKombinacije.repository.JaknaRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class JaknaService {

    @Autowired
    private JaknaRepository repository;

    @Autowired
    private KieContainer kieContainer;

    public Jakna create(Jakna komadOdece){
        return repository.save(komadOdece);
    }

    public List<Jakna> findAll() {
        return repository.findAll();
    }

    public Jakna findOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public PreporuceniKomadi getPreporucenaJakna(UnosDTO unosDTO, User user, List<Jakna> komadi, PreporuceniKomadi preporuceniKomadi) {
        KieSession kieSession = kieContainer.newKieSession("jaknaPersRulesSession");
        for(Jakna komadOdece : komadi){
            kieSession.insert(komadOdece);
        }

        insertOdecaTip(kieSession);
        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);


        kieSession.insert(preporuceniKomadi);
        kieSession.insert(unosDTO);
        kieSession.insert(user);
        kieSession.fireAllRules();

//        HashMap<Long, Double> a = new HashMap<>();
//        a.entrySet().forEach(entry -> {
//            System.out.println((Long)entry.getKey() + " " + entry.getValue());
//        });

        System.out.println(preporuceniKomadi.getPreporuceneJakne().size());
        kieSession.dispose();
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
        kieSession.insert(DressCode.SPORTSKI);
    }

    public void insertOdecaTip(KieSession kieSession){
        kieSession.insert(JaknaEnum.ZIMSKA_JAKNA);
        kieSession.insert(JaknaEnum.SAKO);
        kieSession.insert(JaknaEnum.BUNDA);
        kieSession.insert(JaknaEnum.JAKNA_PRELAZNI);
        kieSession.insert(JaknaEnum.KARDIGAN);
        kieSession.insert(JaknaEnum.KAPUT);
        kieSession.insert(JaknaEnum.TRENERKA);
        kieSession.insert(JaknaEnum.MONTON);
        kieSession.insert(JaknaEnum.PRSLUK);
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
        kieSession.insert(Materijal.SVILA);
        kieSession.insert(Materijal.TEKSAS);
        kieSession.insert(Materijal.VUNA);
        kieSession.insert(Materijal.GUMA);
    }

    public HashMap<DressCode, Integer> makeHashMapColor(){
        HashMap<DressCode, Integer> map = new HashMap<>();
        map.put(DressCode.LEZERAN, 250);
        map.put(DressCode.BLACKTIE, 300);
        map.put(DressCode.FORMALAN, 250);
        map.put(DressCode.IZLAZAK, 250);
        map.put(DressCode.SPORTSKI, 250);
        return map;
    }
}
