package com.ftn.PreporukaOdevneKombinacije;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.dto.UnosNeulogovanDTO;
import com.ftn.PreporukaOdevneKombinacije.helper.TrackingAgendaEventListener;
import com.ftn.PreporukaOdevneKombinacije.model.DonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniDonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.*;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DonjiDeoOpsteTests {
    @Test
    public void TestNoClothingItems(){

        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ddoPersRulesSession");

        UnosNeulogovanDTO unosDTO = unos();
        kieSession.insert(unosDTO);

        kieSession.getAgenda().getAgendaGroup( "bojeMapa" ).setFocus();

        insertTipTela(kieSession);
        insertTipDonjegDela(kieSession);
        insertDubinaDonjegDela(kieSession);
        insertDuzinaDonjegDela(kieSession);
        insertBojaIntenzitet(kieSession);


        kieSession.insert(unosDTO);
        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

        assertEquals(0, preporuceniKomadi.getPreporuceniDonjiDelovi().size() );

        kieSession.dispose();
    }

    @Test
    public void TestOneClothingItems(){
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ddoPersRulesSession");

        UnosNeulogovanDTO unosDTO = unos();
        kieSession.insert(unosDTO);

        kieSession.getAgenda().getAgendaGroup( "bojeMapa" ).setFocus();

        //Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, DuzinaDonjegDela duzina,  Dubina dubina, OdecaPodTip odecaPodTip, DonjiDeoEnum donjiDeoEnum, TipDonjegDela tipDonjegDela, Pol pol
        kieSession.insert(new DonjiDeo(Boja.PLAVA, BojaIntenzitet.SREDNJE, Materijal.TEKSAS, Vreme.SUVO, 0 ,0,"",DuzinaDonjegDela.MAXI, Dubina.MIDI, OdecaPodTip.SIROKA, DonjiDeoEnum.PANTALONE, TipDonjegDela.STRAIGHT, Pol.ZENSKI));

        insertTipTela(kieSession);
        insertTipDonjegDela(kieSession);
        insertDubinaDonjegDela(kieSession);
        insertDuzinaDonjegDela(kieSession);
        insertBojaIntenzitet(kieSession);


        kieSession.insert(unosDTO);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();
        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

        assertEquals(1, preporuceniKomadi.getPreporuceniDonjiDelovi().size() );
        assertEquals(Double.valueOf(0), preporuceniKomadi.getPreporuceniDonjiDelovi().get(0).getPoeni());

        kieSession.dispose();
    }

    @Test
    public void TestFewClothingItems(){

        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ddoPersRulesSession");

        //kieSession.insert(new GornjiDeo(Boja.BELA, Materijal.PAMUK,0,Vreme.SUVO,  1, "", OdecaPodTip.SIROKA, GornjiDeoEnum.MAJICA_KRATKI));
        for(DonjiDeo dd : createDonjiDelovi()){
            kieSession.insert(dd);
        }
        kieSession.getAgenda().getAgendaGroup( "bojeMapa" ).setFocus();

        insertTipTela(kieSession);
        insertTipDonjegDela(kieSession);
        insertDubinaDonjegDela(kieSession);
        insertDuzinaDonjegDela(kieSession);
        insertBojaIntenzitet(kieSession);

        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);

        kieSession.insert(unos());

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

        List<Match> activations = agendaEventListener.getMatchList();
        List<String> ruleNames = new ArrayList<>();
        for (Match m : activations) {
            ruleNames.add(m.getRule().getName());
            System.out.println(m.getRule().getName());
        }


        assertTrue(ruleNames.contains("A3 - Tip tela - obrnuti trougao"));
        assertTrue(ruleNames.contains("B2 - Tip donjeg dela ako je tip tela obrnuti trougao"));
        assertTrue(ruleNames.contains("D3 - Dubina ako je visina izmedju 165 i 175"));

        assertEquals(4, preporuceniKomadi.getPreporuceniDonjiDelovi().size() );

        kieSession.dispose();
    }

    public List<DonjiDeo> createDonjiDelovi(){
        List<DonjiDeo> donjiDeoList = new ArrayList<>();
        donjiDeoList.add(new DonjiDeo(Boja.PLAVA, BojaIntenzitet.SREDNJE, Materijal.TEKSAS, Vreme.SUVO, 0 ,0,"",DuzinaDonjegDela.MAXI, Dubina.MIDI, OdecaPodTip.SIROKA, DonjiDeoEnum.PANTALONE, TipDonjegDela.STRAIGHT, Pol.ZENSKI));
        donjiDeoList.add(new DonjiDeo(Boja.PLAVA, BojaIntenzitet.SREDNJE, Materijal.TEKSAS, Vreme.SUVO, 0 ,0,"",DuzinaDonjegDela.MAXI, Dubina.MIDI, OdecaPodTip.SIROKA, DonjiDeoEnum.PANTALONE, TipDonjegDela.STRAIGHT, Pol.ZENSKI));
        donjiDeoList.add(new DonjiDeo(Boja.PLAVA, BojaIntenzitet.SREDNJE, Materijal.TEKSAS, Vreme.SUVO, 0 ,0,"",DuzinaDonjegDela.MAXI, Dubina.MIDI, OdecaPodTip.SIROKA, DonjiDeoEnum.PANTALONE, TipDonjegDela.STRAIGHT, Pol.ZENSKI));
        donjiDeoList.add(new DonjiDeo(Boja.PLAVA, BojaIntenzitet.SREDNJE, Materijal.TEKSAS, Vreme.SUVO, 0 ,0,"",DuzinaDonjegDela.MAXI, Dubina.MIDI, OdecaPodTip.SIROKA, DonjiDeoEnum.PANTALONE, TipDonjegDela.STRAIGHT, Pol.ZENSKI));
        return donjiDeoList;
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

    public UnosNeulogovanDTO unos(){
        return new UnosNeulogovanDTO(100,90,60,170, DressCode.FORMALAN, BojaKoze.SPRING, "leto", Pol.ZENSKI);
    }
}
