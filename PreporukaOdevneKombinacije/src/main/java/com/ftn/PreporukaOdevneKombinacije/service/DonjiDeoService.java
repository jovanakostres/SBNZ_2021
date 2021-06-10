package com.ftn.PreporukaOdevneKombinacije.service;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.dto.UnosNeulogovanDTO;
import com.ftn.PreporukaOdevneKombinacije.model.DonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniGornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.*;
import com.ftn.PreporukaOdevneKombinacije.repository.DonjiDeoRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DonjiDeoService {

    @Autowired
    private DonjiDeoRepository repository;

    @Autowired
    private KieContainer kieContainer;

    public DonjiDeo create(DonjiDeo komadOdece){
        return repository.save(komadOdece);
    }

    public List<DonjiDeo> findAll() {
        return repository.findAll();
    }

    public DonjiDeo findOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    public PreporuceniKomadi getPreporuceniDonjiDeo(UnosDTO unosDTO, User user, List<DonjiDeo> komadi, PreporuceniKomadi preporuceniKomadi) {
        KieSession kieSession = kieContainer.newKieSession("dDPersRulesSession");
        for(DonjiDeo komadOdece : komadi){
            kieSession.insert(komadOdece);
        }

        insertOdecaTip(kieSession);
        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);



        kieSession.insert(unosDTO);
        kieSession.insert(user);
        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();
//        HashMap<Long, Double> a = new HashMap<>();
//        a.entrySet().forEach(entry -> {
//            System.out.println((Long)entry.getKey() + " " + entry.getValue());
//        });

        System.out.println(preporuceniKomadi.getPreporuceniDonjiDelovi().size());
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
        kieSession.insert(DonjiDeoEnum.HELANKE);
        kieSession.insert(DonjiDeoEnum.KRATKA_SUKNJA);
        kieSession.insert(DonjiDeoEnum.KRATKE_PANTALONE);
        kieSession.insert(DonjiDeoEnum.MAXI_SUKNJA);
        kieSession.insert(DonjiDeoEnum.PANTALONE);
        kieSession.insert(DonjiDeoEnum.SORC);
        kieSession.insert(DonjiDeoEnum.TRENERKA);
        kieSession.insert(DonjiDeoEnum.SUKNJA);
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

    public void insertTipDonjegDela(KieSession kieSession){
        kieSession.insert(TipDonjegDela.PLEATED);
        kieSession.insert(TipDonjegDela.TULIP);
        kieSession.insert(TipDonjegDela.PENCIL);
        kieSession.insert(TipDonjegDela.ALINE);
        kieSession.insert(TipDonjegDela.WRAP);
        kieSession.insert(TipDonjegDela.TRUMPET);
        kieSession.insert(TipDonjegDela.SKINY);
        kieSession.insert(TipDonjegDela.BOOTCUT);
        kieSession.insert(TipDonjegDela.FLARED);
        kieSession.insert(TipDonjegDela.STRAIGHT);
        kieSession.insert(TipDonjegDela.BAGGY);
        kieSession.insert(TipDonjegDela.NONE);
    }

    public void insertDuzinaDonjegDela(KieSession kieSession){
        kieSession.insert(DuzinaDonjegDela.MAXI);
        kieSession.insert(DuzinaDonjegDela.MINI);
        kieSession.insert(DuzinaDonjegDela.MIDI);
    }

    public void insertDubinaDonjegDela(KieSession kieSession){
        kieSession.insert(Dubina.MAXI);
        kieSession.insert(Dubina.MINI);
        kieSession.insert(Dubina.MIDI);
        kieSession.insert(Dubina.NONE);
    }

    public void insertBojaIntenzitet(KieSession kieSession){
        kieSession.insert(BojaIntenzitet.PASTELNA);
        kieSession.insert(BojaIntenzitet.TAMNA);
        kieSession.insert(BojaIntenzitet.SREDNJE);
        kieSession.insert(BojaIntenzitet.SVETLA);
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

    public HashMap<Boja, Integer> makeHashMapColorColor(){
        HashMap<Boja, Integer> map = new HashMap<>();
        map.put(Boja.BELA, 0);
        map.put(Boja.CRNA, 0);
        map.put(Boja.SIVA, 0);
        map.put(Boja.ZELENA, 0);
        map.put(Boja.LJUBICASTA, 0);
        map.put(Boja.ROZA, 0);
        map.put(Boja.PLAVA, 0);
        map.put(Boja.NARANDZASTA, 0);
        map.put(Boja.ZUTA, 0);
        map.put(Boja.CRVENA, 0);
        map.put(Boja.BRAON, 0);

        return map;
    }

    public PreporuceniKomadi getPreporuceniDonjiDeoOpste(UnosNeulogovanDTO unosDTO, List<DonjiDeo> komadi, PreporuceniKomadi preporuceniKomadi) {
        KieSession kieSession;
        if(unosDTO.getPol()==Pol.ZENSKI)
            kieSession = kieContainer.newKieSession("ddoPersRulesSession");
        else
            kieSession = kieContainer.newKieSession("ddomPersRulesSession");
        kieSession.getAgenda().getAgendaGroup( "bojeMapa" ).setFocus();
        for(DonjiDeo komadOdece : komadi){
            kieSession.insert(komadOdece);
        }

        if(unosDTO.getPol()==Pol.ZENSKI) kieSession.setGlobal("hashMapColor", makeHashMapColorColor());

        for(PreporuceniGornjiDeo pgd : preporuceniKomadi.getPreporuceniGornjiDelovi()){
            kieSession.insert(pgd);
        }

        insertTipTela(kieSession);
        insertTipDonjegDela(kieSession);
        insertDubinaDonjegDela(kieSession);
        insertDuzinaDonjegDela(kieSession);
        insertBojaIntenzitet(kieSession);


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
