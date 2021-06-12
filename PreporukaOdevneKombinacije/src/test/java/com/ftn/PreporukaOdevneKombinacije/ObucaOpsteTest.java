package com.ftn.PreporukaOdevneKombinacije;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosNeulogovanDTO;
import com.ftn.PreporukaOdevneKombinacije.helper.TrackingAgendaEventListener;
import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.Obuca;
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

public class ObucaOpsteTest {

    @Test
    public void TestNoClothingItems(){

        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ooPersRulesSession");

        insertStikla(kieSession);
        insertObucaTip(kieSession);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();
        //kieSession.insert(preporuceniKomadi);
        kieSession.insert(unos());

        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

        assertEquals(0, preporuceniKomadi.getPreporucenaObuca().size() );

        kieSession.dispose();
    }

    @Test
    public void TestOneClothingItems(){
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ooPersRulesSession");

        UnosNeulogovanDTO unosDTO = unos();
        kieSession.insert(unosDTO);
        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        //Boja boja, BojaIntenzitet bojaIntenzitet, Materijal materijal, Vreme vreme, int prioritet, double koeficijentOdabira, String image, Pol pol, ObucaEnum obucaTip, Stikla stikla)
        kieSession.insert(new Obuca(Boja.CRNA, BojaIntenzitet.SREDNJE, Materijal.GUMA, Vreme.SUVO,0 , 1, "", Pol.ZENSKI, ObucaEnum.CIPELE, Stikla.SREDNJA ));

        insertTipTela(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertOdecaTip(kieSession);
        insertBoje(kieSession);

        kieSession.insert(unosDTO);

        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

        assertEquals(1, preporuceniKomadi.getPreporucenaObuca().size() );
        assertEquals(Double.valueOf(50.0), preporuceniKomadi.getPreporucenaObuca().get(0).getPoeni());

        kieSession.dispose();
    }

    @Test
    public void TestFewClothingItems(){

        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("ooPersRulesSession");

        kieSession.insert(new Obuca(Boja.CRNA, BojaIntenzitet.SREDNJE, Materijal.GUMA, Vreme.SUVO,0 , 1, "", Pol.ZENSKI, ObucaEnum.CIPELE, Stikla.SREDNJA ));
        kieSession.insert(new Obuca(Boja.CRNA, BojaIntenzitet.SREDNJE, Materijal.GUMA, Vreme.SUVO,0 , 1, "", Pol.ZENSKI, ObucaEnum.SANDALE, Stikla.VISOKA ));
        kieSession.insert(new Obuca(Boja.CRNA, BojaIntenzitet.SREDNJE, Materijal.GUMA, Vreme.SUVO,0 , 1, "", Pol.ZENSKI, ObucaEnum.PATIKE, Stikla.NONE ));
        kieSession.insert(new Obuca(Boja.CRNA, BojaIntenzitet.SREDNJE, Materijal.GUMA, Vreme.SUVO,0 , 1, "", Pol.ZENSKI, ObucaEnum.PAPUCE, Stikla.SREDNJA ));

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        insertTipTela(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertOdecaTip(kieSession);
        insertBoje(kieSession);

        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);

        kieSession.insert(unos());

        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

        List<Match> activations = agendaEventListener.getMatchList();
        List<String> ruleNames = new ArrayList<>();
        //System.out.println("----------");
        for (Match m : activations) {
            ruleNames.add(m.getRule().getName());
          //  System.out.println(m.getRule().getName());
        }
        //System.out.println("----------");

        assertTrue(ruleNames.contains("A5 - 1 - Dresscode formalan leto"));
        assertTrue(ruleNames.contains("F2-2 boje dresscode ne utice leto"));

        assertEquals(4, preporuceniKomadi.getPreporucenaObuca().size() );

        kieSession.dispose();
    }

    public void insertTipTela(KieSession kieSession){
        kieSession.insert(TipTela.TROUGAO);
        kieSession.insert(TipTela.OB_TROUGAO);
        kieSession.insert(TipTela.KRUG);
        kieSession.insert(TipTela.PES_SAT);
        kieSession.insert(TipTela.PRAVOUGAONIK);
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

    public HashMap<DressCode, Integer> makeHashMapColor(){
        HashMap<DressCode, Integer> map = new HashMap<>();
        map.put(DressCode.LEZERAN, 250);
        map.put(DressCode.BLACKTIE, 300);
        map.put(DressCode.FORMALAN, 250);
        map.put(DressCode.IZLAZAK, 250);
        map.put(DressCode.SPORTSKI, 250);
        return map;
    }

    public void insertObucaTip(KieSession kieSession){
        kieSession.insert(ObucaEnum.CIPELE);
        kieSession.insert(ObucaEnum.CIZME);
        kieSession.insert(ObucaEnum.PAPUCE);
        kieSession.insert(ObucaEnum.SANDALE);
        kieSession.insert(ObucaEnum.PATIKE);
    }

    public void insertStikla(KieSession kieSession){
        kieSession.insert(Stikla.NISKA);
        kieSession.insert(Stikla.VISOKA);
        kieSession.insert(Stikla.SREDNJA);
        kieSession.insert(Stikla.NONE);
    }

    public UnosNeulogovanDTO unos(){
        return new UnosNeulogovanDTO(100,90,60,170, DressCode.FORMALAN, BojaKoze.SPRING, "leto", Pol.ZENSKI);
    }
}
