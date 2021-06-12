package com.ftn.PreporukaOdevneKombinacije;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosNeulogovanDTO;
import com.ftn.PreporukaOdevneKombinacije.helper.TrackingAgendaEventListener;
import com.ftn.PreporukaOdevneKombinacije.model.DonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GornjiDeoOpsteTest {

    @Test
    public void TestNoClothingItems(){

        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("gdoPersRulesSession");

        UnosNeulogovanDTO unosDTO = unos();
        kieSession.insert(unosDTO);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);
        insertBojaIntenzitet(kieSession);
        insertBoje(kieSession);
        insertIzrez(kieSession);

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
        KieSession kieSession = kieContainer.newKieSession("gdoPersRulesSession");

        UnosNeulogovanDTO unosDTO = unos();

        //Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, String image, DuzinaRukava duzina, Izrez izrez, OdecaPodTip odecaPodTip, GornjiDeoEnum odecaTip, Pol pol
        kieSession.insert(new GornjiDeo(Boja.PLAVA, BojaIntenzitet.SREDNJE, Materijal.PAMUK, Vreme.SUVO, 0 ,0,"", Izrez.DUBOK, OdecaPodTip.SIROKA, GornjiDeoEnum.BLUZA, DuzinaRukava.SREDNJI, Pol.ZENSKI));

        insertBojaIntenzitet(kieSession);
        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);
        insertBoje(kieSession);
        insertIzrez(kieSession);

        kieSession.insert(unosDTO);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();
        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

        assertEquals(1, preporuceniKomadi.getPreporuceniGornjiDelovi().size() );
        assertEquals(Double.valueOf(0), preporuceniKomadi.getPreporuceniGornjiDelovi().get(0).getPoeni());

        kieSession.dispose();
    }

    @Test
    public void TestFewClothingItems(){

        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("gdoPersRulesSession");

        kieSession.insert(new GornjiDeo(Boja.PLAVA, BojaIntenzitet.SREDNJE, Materijal.PAMUK, Vreme.SUVO, 0 ,0,"", Izrez.DUBOK, OdecaPodTip.SIROKA, GornjiDeoEnum.BLUZA, DuzinaRukava.SREDNJI, Pol.ZENSKI));
        kieSession.insert(new GornjiDeo(Boja.BELA, BojaIntenzitet.SREDNJE, Materijal.PAMUK, Vreme.SUVO, 0 ,0,"", Izrez.DUBOK, OdecaPodTip.SIROKA, GornjiDeoEnum.BLUZA, DuzinaRukava.DUGI, Pol.ZENSKI));
        kieSession.insert(new GornjiDeo(Boja.LJUBICASTA, BojaIntenzitet.SREDNJE, Materijal.PAMUK, Vreme.SUVO, 0 ,0,"", Izrez.DUBOK, OdecaPodTip.SIROKA, GornjiDeoEnum.BLUZA, DuzinaRukava.BRETELE, Pol.ZENSKI));
        kieSession.insert(new GornjiDeo(Boja.NARANDZASTA, BojaIntenzitet.SREDNJE, Materijal.PAMUK, Vreme.SUVO, 0 ,0,"", Izrez.DUBOK, OdecaPodTip.SIROKA, GornjiDeoEnum.BLUZA, DuzinaRukava.SREDNJI, Pol.ZENSKI));

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);
        insertBojaIntenzitet(kieSession);
        insertBoje(kieSession);
        insertIzrez(kieSession);

        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);

        kieSession.insert(unos());

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

        List<Match> activations = agendaEventListener.getMatchList();
        List<String> ruleNames = new ArrayList<>();
        //System.out.println("----------");
        for (Match m : activations) {
            ruleNames.add(m.getRule().getName());
            //System.out.println(m.getRule().getName());
        }
        //System.out.println("----------");

        assertTrue(ruleNames.contains("A3 - Tip tela - obrnuti trougao"));
        assertTrue(ruleNames.contains("F2-2 boje dresscode ne utice leto"));

        assertEquals(4, preporuceniKomadi.getPreporuceniGornjiDelovi().size() );

        kieSession.dispose();
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

    public UnosNeulogovanDTO unos(){
        return new UnosNeulogovanDTO(100,90,60,170, DressCode.FORMALAN, BojaKoze.SPRING, "leto", Pol.ZENSKI);
    }
}
