package com.ftn.PreporukaOdevneKombinacije;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosNeulogovanDTO;
import com.ftn.PreporukaOdevneKombinacije.helper.TrackingAgendaEventListener;
import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.Jakna;
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

public class JaknaOpsteTest {

    @Test
    public void TestNoClothingItems(){

        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("joPersRulesSession");

        UnosNeulogovanDTO unosDTO = unos();
        kieSession.insert(unosDTO);

        insertTipTela(kieSession);
        insertOdecaTip(kieSession);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        //kieSession.insert(preporuceniKomadi);
        kieSession.insert(unosDTO);

        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

        assertEquals(0, preporuceniKomadi.getPreporuceneJakne().size() );

        kieSession.dispose();
    }

    @Test
    public void TestOneClothingItems(){
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("joPersRulesSession");

        UnosNeulogovanDTO unosDTO = unos();
        kieSession.insert(unosDTO);

        //Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, Pol pol, JaknaEnum odecajTip, OdecaPodTip odecaPodTip
        kieSession.insert(new Jakna(Boja.CRNA, BojaIntenzitet.SREDNJE, Materijal.KOZA, Vreme.SUVO,0,1,"", Pol.ZENSKI, JaknaEnum.JAKNA, OdecaPodTip.SIROKA));

        insertTipTela(kieSession);
        insertOdecaTip(kieSession);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        //kieSession.insert(preporuceniKomadi);
        kieSession.insert(unosDTO);

        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

        assertEquals(1, preporuceniKomadi.getPreporuceneJakne().size() );
        assertEquals(Double.valueOf(0), preporuceniKomadi.getPreporuceneJakne().get(0).getPoeni());

        kieSession.dispose();
    }

    @Test
    public void TestFewClothingItems(){

        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("joPersRulesSession");

        kieSession.insert(new Jakna(Boja.CRNA, BojaIntenzitet.SREDNJE, Materijal.KOZA, Vreme.SUVO,0,1,"", Pol.ZENSKI, JaknaEnum.JAKNA, OdecaPodTip.SIROKA));
        kieSession.insert(new Jakna(Boja.CRNA, BojaIntenzitet.SREDNJE, Materijal.PAMUK, Vreme.SUVO,0,1,"", Pol.ZENSKI, JaknaEnum.TRENERKA, OdecaPodTip.SIROKA));
        kieSession.insert(new Jakna(Boja.CRNA, BojaIntenzitet.SREDNJE, Materijal.KRZNO, Vreme.SUVO,0,1,"", Pol.ZENSKI, JaknaEnum.BUNDA, OdecaPodTip.SIROKA));
        kieSession.insert(new Jakna(Boja.CRNA, BojaIntenzitet.SREDNJE, Materijal.POLIESTER, Vreme.SUVO,0,1,"", Pol.ZENSKI, JaknaEnum.KAPUT, OdecaPodTip.SIROKA));

        insertTipTela(kieSession);
        insertOdecaTip(kieSession);


        //kieSession.insert(preporuceniKomadi);

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

        assertTrue(ruleNames.contains("A5 - 1 - Dresscode formalan leto"));
        assertTrue(ruleNames.contains("F2-2 boje dresscode ne utice leto"));

        assertEquals(4, preporuceniKomadi.getPreporuceneJakne().size() );

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

    public UnosNeulogovanDTO unos(){
        return new UnosNeulogovanDTO(100,90,60,170, DressCode.FORMALAN, BojaKoze.SPRING, "leto", Pol.ZENSKI);
    }
}
