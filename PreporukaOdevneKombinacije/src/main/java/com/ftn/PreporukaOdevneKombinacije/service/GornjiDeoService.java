package com.ftn.PreporukaOdevneKombinacije.service;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.dto.UnosNeulogovanDTO;
import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.*;
import com.ftn.PreporukaOdevneKombinacije.repository.GornjiDeoRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class GornjiDeoService {

    @Autowired
    private GornjiDeoRepository repository;

    @Autowired
    private KieContainer kieContainer;

    public GornjiDeo create(GornjiDeo komadOdece){
        return repository.save(komadOdece);
    }

    public List<GornjiDeo> findAll() {
        return repository.findAll();
    }

    public GornjiDeo findOne(Long id) {
        return repository.findById(id).orElse(null);
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


        kieSession.insert(unosDTO);
        kieSession.insert(user);
        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();
//        HashMap<Long, Double> a = new HashMap<>();
//        a.entrySet().forEach(entry -> {
//            System.out.println((Long)entry.getKey() + " " + entry.getValue());
//        });

        System.out.println(preporuceniKomadi.getPreporuceniGornjiDelovi().size());
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
        kieSession.insert(GornjiDeoEnum.BLUZA);
        kieSession.insert(GornjiDeoEnum.DUKS);
        kieSession.insert(GornjiDeoEnum.DZEMPER);
        kieSession.insert(GornjiDeoEnum.KOSULJA);
        kieSession.insert(GornjiDeoEnum.TUNIKA);
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
        kieSession.insert(Materijal.SVILA);
        kieSession.insert(Materijal.TEKSAS);
        kieSession.insert(Materijal.VUNA);
        kieSession.insert(Materijal.GUMA);
    }

    public void insertIzrez(KieSession kieSession){
        kieSession.insert(Izrez.DUBOK);
        kieSession.insert(Izrez.PLITAK);
        kieSession.insert(Izrez.NONE);
    }

    public void insertBojaIntenzitet(KieSession kieSession){
        kieSession.insert(BojaIntenzitet.TAMNA);
        kieSession.insert(BojaIntenzitet.SREDNJE);
        kieSession.insert(BojaIntenzitet.SVETLA);
        kieSession.insert(BojaIntenzitet.PASTELNA);
    }

    public void insertBoje(KieSession kieSession){
        kieSession.insert(Boja.CRNA);
        kieSession.insert(Boja.BELA);
        kieSession.insert(Boja.SIVA);
        kieSession.insert(Boja.ZELENA);
        kieSession.insert(Boja.LJUBICASTA);
        kieSession.insert(Boja.ROZA);
        kieSession.insert(Boja.PLAVA);
        kieSession.insert(Boja.NARANDZASTA);
        kieSession.insert(Boja.ZUTA);
        kieSession.insert(Boja.CRVENA);
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

    public PreporuceniKomadi getPreporuceniGornjiDeoOpste(UnosNeulogovanDTO unosDTO, List<GornjiDeo> komadi, PreporuceniKomadi preporuceniKomadi) {
        KieSession kieSession = kieContainer.newKieSession("gdoPersRulesSession");
        for(GornjiDeo komadOdece : komadi){
            kieSession.insert(komadOdece);
        }

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);
        insertBojaIntenzitet(kieSession);
        insertBoje(kieSession);
        insertIzrez(kieSession);



        //kieSession.insert(preporuceniKomadi);
        kieSession.insert(unosDTO);

        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

//        HashMap<Long, Double> a = new HashMap<>();
//        a.entrySet().forEach(entry -> {
//            System.out.println((Long)entry.getKey() + " " + entry.getValue());
//        });


        kieSession.dispose();

        return preporuceniKomadi;
    }
}
