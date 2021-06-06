package com.ftn.PreporukaOdevneKombinacije.service;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.model.DonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.Obuca;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.*;
import com.ftn.PreporukaOdevneKombinacije.repository.DonjiDeoRepository;
import com.ftn.PreporukaOdevneKombinacije.repository.ObucaRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ObucaService {

    @Autowired
    private ObucaRepository repository;

    @Autowired
    private KieContainer kieContainer;

    public Obuca create(Obuca komadOdece){
        return repository.save(komadOdece);
    }

    public List<Obuca> findAll() {
        return repository.findAll();
    }

    public Obuca findOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public PreporuceniKomadi getPreporuceniDonjiDeo(UnosDTO unosDTO, User user, List<Obuca> komadi, PreporuceniKomadi preporuceniKomadi) {
        KieSession kieSession = kieContainer.newKieSession("obucaPersRulesSession");
        for(Obuca komadOdece : komadi){
            kieSession.insert(komadOdece);
        }

        insertOdecaTip(kieSession);
        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertBoje(kieSession);

        kieSession.insert(unosDTO);
        kieSession.insert(user);
        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

//        HashMap<Long, Double> a = new HashMap<>();
//        a.entrySet().forEach(entry -> {
//            System.out.println((Long)entry.getKey() + " " + entry.getValue());
//        });

        System.out.println(preporuceniKomadi.getPreporucenaObuca().size());
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
        kieSession.insert(ObucaEnum.CIPELE);
        kieSession.insert(ObucaEnum.CIZME);
        kieSession.insert(ObucaEnum.PAPUCE);
        kieSession.insert(ObucaEnum.PATIKE);
        kieSession.insert(ObucaEnum.SANDALE);
    }

    public void insertBoje(KieSession kieSession){
        kieSession.insert(Boja.BELA);
        kieSession.insert(Boja.BRAON);
        kieSession.insert(Boja.LJUBICASTA);
        kieSession.insert(Boja.CRNA);
        kieSession.insert(Boja.CRVENA);
        kieSession.insert(Boja.NARANDZASTA);
        kieSession.insert(Boja.ROZA);
        kieSession.insert(Boja.SIVA);
        kieSession.insert(Boja.ZELENA);
        kieSession.insert(Boja.ZUTA);
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
