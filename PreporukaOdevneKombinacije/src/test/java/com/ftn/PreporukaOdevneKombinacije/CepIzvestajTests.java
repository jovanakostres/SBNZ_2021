package com.ftn.PreporukaOdevneKombinacije;

import com.ftn.PreporukaOdevneKombinacije.model.DonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.Jakna;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.*;
import com.ftn.PreporukaOdevneKombinacije.model.event.IzabranKomadOdeceEvent;
import com.ftn.PreporukaOdevneKombinacije.model.event.IzabranaKombinacijaEvent;
import com.ftn.PreporukaOdevneKombinacije.model.event.OdbijenKomadEvent;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.TimedRuleExecutionOption;
import org.springframework.security.core.parameters.P;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CepIzvestajTests {

    @Test
    public void dana7NajviseIzabranoTest() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        KieSession kSession = kc.newKieSession("cepIzvestajRulesnPseudoClock");
        kSession.getAgenda().getAgendaGroup( "7danaNajvise" ).setFocus();
        SessionPseudoClock clock = kSession.getSessionClock();

        GornjiDeo gornjiDeo = new GornjiDeo(1L, Boja.BELA, Materijal.PAMUK,0, Vreme.SUVO,  1, "", true,OdecaPodTip.SIROKA, GornjiDeoEnum.MAJICA_KRATKI);
        GornjiDeo gornjiDeo2 = new GornjiDeo(2L, Boja.CRNA, Materijal.PAMUK,0,Vreme.VLAZNO,  1, "", true, OdecaPodTip.USKA, GornjiDeoEnum.DUKS);

        DonjiDeo donjiDeo = new DonjiDeo(3L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", true, DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);
        DonjiDeo donjiDeo2 = new DonjiDeo(4L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", true, DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);

        IzabranKomadOdeceEvent izabranKomadOdeceEvent = new IzabranKomadOdeceEvent(gornjiDeo);
        IzabranKomadOdeceEvent izabranKomadOdeceEvent1 = new IzabranKomadOdeceEvent(gornjiDeo);
        IzabranKomadOdeceEvent izabranKomadOdeceEvent2 = new IzabranKomadOdeceEvent(gornjiDeo2);

        IzabranKomadOdeceEvent izabranKomadOdeceEvent3 = new IzabranKomadOdeceEvent(donjiDeo);
        IzabranKomadOdeceEvent izabranKomadOdeceEvent4 = new IzabranKomadOdeceEvent(donjiDeo);
        IzabranKomadOdeceEvent izabranKomadOdeceEvent5 = new IzabranKomadOdeceEvent(donjiDeo2);

        kSession.insert(izabranKomadOdeceEvent1);
        kSession.insert(izabranKomadOdeceEvent);
        kSession.insert(izabranKomadOdeceEvent2);

        kSession.insert(izabranKomadOdeceEvent3);
        kSession.insert(izabranKomadOdeceEvent4);
        kSession.insert(izabranKomadOdeceEvent5);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        kSession.insert(preporuceniKomadi);
        kSession.fireAllRules();

        assertEquals( Long.valueOf(1), preporuceniKomadi.getPreporuceniGornjiDelovi().get(0).getGornjiDeo().getId());
        assertEquals( Double.valueOf(2), preporuceniKomadi.getPreporuceniGornjiDelovi().get(0).getPoeni());

        assertEquals( Long.valueOf(3), preporuceniKomadi.getPreporuceniDonjiDelovi().get(0).getDonjiDeo().getId());
        assertEquals( Double.valueOf(2), preporuceniKomadi.getPreporuceniDonjiDelovi().get(0).getPoeni());

        clock.advanceTime(6, TimeUnit.DAYS);
        kSession.getAgenda().getAgendaGroup( "7danaNajvise" ).setFocus();
        PreporuceniKomadi preporuceniKomadi1 = new PreporuceniKomadi();
        kSession.insert(preporuceniKomadi1);
        kSession.fireAllRules();
        assertEquals( Long.valueOf(1), preporuceniKomadi1.getPreporuceniGornjiDelovi().get(0).getGornjiDeo().getId());
        assertEquals( Double.valueOf(2), preporuceniKomadi1.getPreporuceniGornjiDelovi().get(0).getPoeni());

        assertEquals( Long.valueOf(3), preporuceniKomadi1.getPreporuceniDonjiDelovi().get(0).getDonjiDeo().getId());
        assertEquals( Double.valueOf(2), preporuceniKomadi1.getPreporuceniDonjiDelovi().get(0).getPoeni());

        clock.advanceTime(1, TimeUnit.DAYS);
        PreporuceniKomadi preporuceniKomadi2 = new PreporuceniKomadi();
        kSession.getAgenda().getAgendaGroup( "7danaNajvise" ).setFocus();
        kSession.insert(preporuceniKomadi2);
        kSession.fireAllRules();
        assertEquals( 0, preporuceniKomadi2.getPreporuceniGornjiDelovi().size());

    }


    @Test
    public void dana7SveIzabranoTest() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        KieSession kSession = kc.newKieSession("cepIzvestajRulesnPseudoClock");
        kSession.getAgenda().getAgendaGroup( "7danaNajvise" ).setFocus();
        SessionPseudoClock clock = kSession.getSessionClock();

        GornjiDeo gornjiDeo = new GornjiDeo(1L, Boja.BELA, Materijal.PAMUK,0, Vreme.SUVO,  1, "", true, OdecaPodTip.SIROKA, GornjiDeoEnum.MAJICA_KRATKI);
        GornjiDeo gornjiDeo2 = new GornjiDeo(2L, Boja.CRNA, Materijal.PAMUK,0,Vreme.VLAZNO,  1, "", true, OdecaPodTip.USKA, GornjiDeoEnum.DUKS);

        DonjiDeo donjiDeo = new DonjiDeo(3L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", true, DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);
        DonjiDeo donjiDeo2 = new DonjiDeo(4L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", true, DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);

        IzabranKomadOdeceEvent izabranKomadOdeceEvent = new IzabranKomadOdeceEvent(gornjiDeo);
        IzabranKomadOdeceEvent izabranKomadOdeceEvent1 = new IzabranKomadOdeceEvent(gornjiDeo);
        IzabranKomadOdeceEvent izabranKomadOdeceEvent2 = new IzabranKomadOdeceEvent(gornjiDeo2);

        IzabranKomadOdeceEvent izabranKomadOdeceEvent3 = new IzabranKomadOdeceEvent(donjiDeo);
        IzabranKomadOdeceEvent izabranKomadOdeceEvent4 = new IzabranKomadOdeceEvent(donjiDeo);
        IzabranKomadOdeceEvent izabranKomadOdeceEvent5 = new IzabranKomadOdeceEvent(donjiDeo2);

        kSession.insert(izabranKomadOdeceEvent1);
        kSession.insert(izabranKomadOdeceEvent);
        kSession.insert(izabranKomadOdeceEvent2);

        kSession.insert(izabranKomadOdeceEvent3);
        kSession.insert(izabranKomadOdeceEvent4);
        kSession.insert(izabranKomadOdeceEvent5);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        kSession.fireAllRules();

        kSession.getAgenda().getAgendaGroup( "7danaSvePreporucivano" ).setFocus();
        kSession.insert(preporuceniKomadi);
        kSession.fireAllRules();

        assertEquals( 2, preporuceniKomadi.getPreporuceniGornjiDelovi().size());

        assertEquals( 2, preporuceniKomadi.getPreporuceniDonjiDelovi().size());

        clock.advanceTime(6, TimeUnit.DAYS);
        kSession.getAgenda().getAgendaGroup( "7danaSvePreporucivano" ).setFocus();
        PreporuceniKomadi preporuceniKomadi1 = new PreporuceniKomadi();
        kSession.insert(preporuceniKomadi1);
        kSession.fireAllRules();
        assertEquals( 2, preporuceniKomadi1.getPreporuceniGornjiDelovi().size());

        assertEquals( 2, preporuceniKomadi1.getPreporuceniDonjiDelovi().size());

        clock.advanceTime(1, TimeUnit.DAYS);
        PreporuceniKomadi preporuceniKomadi2 = new PreporuceniKomadi();
        kSession.getAgenda().getAgendaGroup( "7danaSvePreporucivano" ).setFocus();
        kSession.insert(preporuceniKomadi2);
        kSession.fireAllRules();
        assertEquals( 0, preporuceniKomadi2.getPreporuceniGornjiDelovi().size());

    }


    @Test
    public void sata24SveIzabranoTest() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        KieSession kSession = kc.newKieSession("cepIzvestajRulesnPseudoClock");
        kSession.getAgenda().getAgendaGroup( "7danaNajvise" ).setFocus();
        SessionPseudoClock clock = kSession.getSessionClock();

        GornjiDeo gornjiDeo = new GornjiDeo(1L, Boja.BELA, Materijal.PAMUK,0, Vreme.SUVO,  1, "", true, OdecaPodTip.SIROKA, GornjiDeoEnum.MAJICA_KRATKI);
        GornjiDeo gornjiDeo2 = new GornjiDeo(2L, Boja.CRNA, Materijal.PAMUK,0,Vreme.VLAZNO,  1, "", true, OdecaPodTip.USKA, GornjiDeoEnum.DUKS);

        DonjiDeo donjiDeo = new DonjiDeo(3L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", true, DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);
        DonjiDeo donjiDeo2 = new DonjiDeo(4L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", true, DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);

        System.out.println(GornjiDeo.class.getName());
        IzabranKomadOdeceEvent izabranKomadOdeceEvent = new IzabranKomadOdeceEvent(gornjiDeo);
        IzabranKomadOdeceEvent izabranKomadOdeceEvent1 = new IzabranKomadOdeceEvent(gornjiDeo);
        IzabranKomadOdeceEvent izabranKomadOdeceEvent2 = new IzabranKomadOdeceEvent(gornjiDeo2);

        IzabranKomadOdeceEvent izabranKomadOdeceEvent3 = new IzabranKomadOdeceEvent(donjiDeo);
        IzabranKomadOdeceEvent izabranKomadOdeceEvent4 = new IzabranKomadOdeceEvent(donjiDeo);
        IzabranKomadOdeceEvent izabranKomadOdeceEvent5 = new IzabranKomadOdeceEvent(donjiDeo2);

        kSession.insert(izabranKomadOdeceEvent1);
        kSession.insert(izabranKomadOdeceEvent);
        kSession.insert(izabranKomadOdeceEvent2);

        kSession.insert(izabranKomadOdeceEvent3);
        kSession.insert(izabranKomadOdeceEvent4);
        kSession.insert(izabranKomadOdeceEvent5);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        kSession.fireAllRules();

        kSession.getAgenda().getAgendaGroup( "24sataSvePreporucivano" ).setFocus();
        kSession.insert(preporuceniKomadi);
        kSession.fireAllRules();

        assertEquals( 2, preporuceniKomadi.getPreporuceniGornjiDelovi().size());

        assertEquals( 2, preporuceniKomadi.getPreporuceniDonjiDelovi().size());

        clock.advanceTime(23, TimeUnit.HOURS);
        kSession.getAgenda().getAgendaGroup( "24sataSvePreporucivano" ).setFocus();
        PreporuceniKomadi preporuceniKomadi1 = new PreporuceniKomadi();
        kSession.insert(preporuceniKomadi1);
        kSession.fireAllRules();
        assertEquals( 2, preporuceniKomadi1.getPreporuceniGornjiDelovi().size());

        assertEquals( 2, preporuceniKomadi1.getPreporuceniDonjiDelovi().size());

        clock.advanceTime(1, TimeUnit.HOURS);
        kSession.getAgenda().getAgendaGroup( "24sataSvePreporucivano" ).setFocus();
        PreporuceniKomadi preporuceniKomadi2 = new PreporuceniKomadi();
        kSession.insert(preporuceniKomadi2);
        kSession.fireAllRules();
        assertEquals( 0, preporuceniKomadi2.getPreporuceniGornjiDelovi().size());

        assertEquals( 0, preporuceniKomadi2.getPreporuceniDonjiDelovi().size());

    }


    @Test
    public void dana7NajviseaIzabranoUzKomadTest() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        KieSession kSession = kc.newKieSession("cepIzvestajKombRulesnPseudoClock");
        SessionPseudoClock clock = kSession.getSessionClock();

        GornjiDeo gornjiDeo = new GornjiDeo(1L, Boja.BELA, Materijal.PAMUK,0, Vreme.SUVO,  1, "", true, OdecaPodTip.SIROKA, GornjiDeoEnum.MAJICA_KRATKI);
        GornjiDeo gornjiDeo2 = new GornjiDeo(2L, Boja.CRNA, Materijal.PAMUK,0,Vreme.VLAZNO,  1, "", true, OdecaPodTip.USKA, GornjiDeoEnum.DUKS);

        DonjiDeo donjiDeo = new DonjiDeo(3L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", true,DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);
        DonjiDeo donjiDeo2 = new DonjiDeo(4L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", true, DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);

        Jakna jakna = new Jakna(5L, Boja.BELA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", true, JaknaEnum.JAKNA_PRELAZNI ,OdecaPodTip.SIROKA);
        Jakna jakna1 = new Jakna(6L, Boja.BELA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", true, JaknaEnum.JAKNA_PRELAZNI ,OdecaPodTip.SIROKA);

        IzabranaKombinacijaEvent izabranaKombinacijaEvent = new IzabranaKombinacijaEvent(gornjiDeo, donjiDeo);
        IzabranaKombinacijaEvent izabranaKombinacijaEvent1 = new IzabranaKombinacijaEvent(gornjiDeo, donjiDeo2);
        IzabranaKombinacijaEvent izabranaKombinacijaEvent2 = new IzabranaKombinacijaEvent(gornjiDeo, donjiDeo);
        IzabranaKombinacijaEvent izabranaKombinacijaEvent3 = new IzabranaKombinacijaEvent(gornjiDeo2, donjiDeo);
        IzabranaKombinacijaEvent izabranaKombinacijaEvent4 = new IzabranaKombinacijaEvent(jakna, donjiDeo);
        IzabranaKombinacijaEvent izabranaKombinacijaEvent5 = new IzabranaKombinacijaEvent(jakna1, donjiDeo);
        IzabranaKombinacijaEvent izabranaKombinacijaEvent6 = new IzabranaKombinacijaEvent(jakna, donjiDeo);

        kSession.insert(izabranaKombinacijaEvent);
        kSession.insert(izabranaKombinacijaEvent1);
        kSession.insert(izabranaKombinacijaEvent2);
        kSession.insert(izabranaKombinacijaEvent3);
        kSession.insert(izabranaKombinacijaEvent4);
        kSession.insert(izabranaKombinacijaEvent6);
        kSession.insert(izabranaKombinacijaEvent5);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        kSession.insert(preporuceniKomadi);
        kSession.insert(donjiDeo);
        kSession.fireAllRules();

        assertEquals( Long.valueOf(1), preporuceniKomadi.getPreporuceniGornjiDelovi().get(0).getGornjiDeo().getId());
        assertEquals( Double.valueOf(2), preporuceniKomadi.getPreporuceniGornjiDelovi().get(0).getPoeni());

        assertEquals( Long.valueOf(5), preporuceniKomadi.getPreporuceneJakne().get(0).getJakna().getId());
        assertEquals( Double.valueOf(2), preporuceniKomadi.getPreporuceneJakne().get(0).getPoeni());

        clock.advanceTime(6, TimeUnit.DAYS);
        PreporuceniKomadi preporuceniKomadi1 = new PreporuceniKomadi();
        kSession.insert(preporuceniKomadi1);
        kSession.fireAllRules();
        assertEquals( Long.valueOf(1), preporuceniKomadi.getPreporuceniGornjiDelovi().get(0).getGornjiDeo().getId());
        assertEquals( Double.valueOf(2), preporuceniKomadi.getPreporuceniGornjiDelovi().get(0).getPoeni());

        assertEquals( Long.valueOf(5), preporuceniKomadi.getPreporuceneJakne().get(0).getJakna().getId());
        assertEquals( Double.valueOf(2), preporuceniKomadi.getPreporuceneJakne().get(0).getPoeni());

        clock.advanceTime(1, TimeUnit.DAYS);
        PreporuceniKomadi preporuceniKomadi2 = new PreporuceniKomadi();
        kSession.insert(preporuceniKomadi2);
        kSession.fireAllRules();
        assertEquals( 0, preporuceniKomadi2.getPreporuceniGornjiDelovi().size());

    }

    @Test
    public void odbijenKomad3Puta5DanaTest() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        KieSession kSession = kc.newKieSession("cepOdbijenbRulesnPseudoClock");
        SessionPseudoClock clock = kSession.getSessionClock();
//        KieSessionConfiguration ksconf = ks.newKieSessionConfiguration();
//        ksconf.setOption( TimedRuleExecutionOption.YES );

        GornjiDeo gornjiDeo = new GornjiDeo(1L, Boja.BELA, Materijal.PAMUK,0, Vreme.SUVO,  1, "", true, OdecaPodTip.SIROKA, GornjiDeoEnum.MAJICA_KRATKI);
        GornjiDeo gornjiDeo2 = new GornjiDeo(2L, Boja.CRNA, Materijal.PAMUK,0,Vreme.VLAZNO,  1, "", true, OdecaPodTip.USKA, GornjiDeoEnum.DUKS);

        DonjiDeo donjiDeo = new DonjiDeo(3L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", true,DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);
        DonjiDeo donjiDeo2 = new DonjiDeo(4L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", true, DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);

        Jakna jakna = new Jakna(5L, Boja.BELA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", true, JaknaEnum.JAKNA_PRELAZNI ,OdecaPodTip.SIROKA);
        Jakna jakna1 = new Jakna(6L, Boja.BELA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", true, JaknaEnum.JAKNA_PRELAZNI ,OdecaPodTip.SIROKA);

        OdbijenKomadEvent odbijenKomadEvent = new OdbijenKomadEvent(gornjiDeo);
        OdbijenKomadEvent odbijenKomadEvent1 = new OdbijenKomadEvent(gornjiDeo);
        OdbijenKomadEvent odbijenKomadEvent2 = new OdbijenKomadEvent(gornjiDeo);


        kSession.insert(odbijenKomadEvent);
        kSession.insert(odbijenKomadEvent1);
        kSession.insert(odbijenKomadEvent2);
        kSession.insert(gornjiDeo);

        kSession.fireAllRules();

        assertFalse(gornjiDeo.isAktivan());

        clock.advanceTime(13, TimeUnit.DAYS);
        kSession.fireAllRules();
        assertFalse(gornjiDeo.isAktivan());

        clock.advanceTime(16, TimeUnit.DAYS);
        kSession.fireAllRules();
        assertTrue(gornjiDeo.isAktivan());

    }


}
