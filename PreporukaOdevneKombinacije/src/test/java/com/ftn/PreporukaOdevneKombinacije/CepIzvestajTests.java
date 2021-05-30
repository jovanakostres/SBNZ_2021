package com.ftn.PreporukaOdevneKombinacije;

import com.ftn.PreporukaOdevneKombinacije.model.DonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.*;
import com.ftn.PreporukaOdevneKombinacije.model.event.IzabranKomadOdeceEvent;
import org.drools.core.time.SessionPseudoClock;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.security.core.parameters.P;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CepIzvestajTests {

    @Test
    public void dana7NajviseIzabranoTest() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.newKieClasspathContainer();
        KieSession kSession = kc.newKieSession("cepIzvestajRulesnPseudoClock");
        kSession.getAgenda().getAgendaGroup( "7danaNajvise" ).setFocus();
        SessionPseudoClock clock = kSession.getSessionClock();

        GornjiDeo gornjiDeo = new GornjiDeo(1L, Boja.BELA, Materijal.PAMUK,0, Vreme.SUVO,  1, "", OdecaPodTip.SIROKA, GornjiDeoEnum.MAJICA_KRATKI);
        GornjiDeo gornjiDeo2 = new GornjiDeo(2L, Boja.CRNA, Materijal.PAMUK,0,Vreme.VLAZNO,  1, "", OdecaPodTip.USKA, GornjiDeoEnum.DUKS);

        DonjiDeo donjiDeo = new DonjiDeo(3L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);
        DonjiDeo donjiDeo2 = new DonjiDeo(4L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);

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

        GornjiDeo gornjiDeo = new GornjiDeo(1L, Boja.BELA, Materijal.PAMUK,0, Vreme.SUVO,  1, "", OdecaPodTip.SIROKA, GornjiDeoEnum.MAJICA_KRATKI);
        GornjiDeo gornjiDeo2 = new GornjiDeo(2L, Boja.CRNA, Materijal.PAMUK,0,Vreme.VLAZNO,  1, "", OdecaPodTip.USKA, GornjiDeoEnum.DUKS);

        DonjiDeo donjiDeo = new DonjiDeo(3L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);
        DonjiDeo donjiDeo2 = new DonjiDeo(4L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);

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

        GornjiDeo gornjiDeo = new GornjiDeo(1L, Boja.BELA, Materijal.PAMUK,0, Vreme.SUVO,  1, "", OdecaPodTip.SIROKA, GornjiDeoEnum.MAJICA_KRATKI);
        GornjiDeo gornjiDeo2 = new GornjiDeo(2L, Boja.CRNA, Materijal.PAMUK,0,Vreme.VLAZNO,  1, "", OdecaPodTip.USKA, GornjiDeoEnum.DUKS);

        DonjiDeo donjiDeo = new DonjiDeo(3L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);
        DonjiDeo donjiDeo2 = new DonjiDeo(4L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA);

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

    }
}
