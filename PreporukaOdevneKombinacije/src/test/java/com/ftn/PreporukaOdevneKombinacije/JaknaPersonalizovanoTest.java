package com.ftn.PreporukaOdevneKombinacije;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.helper.TrackingAgendaEventListener;
import com.ftn.PreporukaOdevneKombinacije.model.DonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.Jakna;
import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporucenaJakna;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniDonjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.*;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JaknaPersonalizovanoTest {

    @Test
    public void TestNoClothingItems(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("jaknaPersRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();
        kieSession.insert(preporuceniKomadi);
        kieSession.insert(createUnos());
        kieSession.fireAllRules();

        assertEquals(0, preporuceniKomadi.getPreporuceneJakne().size() );

        kieSession.dispose();
    }

    @Test
    public void TestOneClothingItems(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("jaknaPersRulesSession");
        kieSession.insert(user);
        kieSession.insert(new Jakna(Boja.BELA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", JaknaEnum.JAKNA_PRELAZNI , OdecaPodTip.SIROKA));

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();
        kieSession.insert(preporuceniKomadi);
        kieSession.insert(createUnos());
        kieSession.fireAllRules();

        assertEquals(1, preporuceniKomadi.getPreporuceneJakne().size() );
        assertEquals(Double.valueOf(95), preporuceniKomadi.getPreporuceneJakne().get(0).getPoeni());

        kieSession.dispose();
    }

    @Test
    public void TestOneClothingItemsDresscodeBlackTie(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("jaknaPersRulesSession");
        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);

        kieSession.insert(user);
        kieSession.insert(new Jakna(1L, Boja.BELA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", JaknaEnum.JAKNA_PRELAZNI ,OdecaPodTip.SIROKA));

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();
        kieSession.insert(preporuceniKomadi);
        kieSession.insert(createUnosBlackTie());
        kieSession.fireAllRules();

        List<Match> activations = agendaEventListener.getMatchList();
        List<String> ruleNames = new ArrayList<>();
        for (Match m : activations) {
            ruleNames.add(m.getRule().getName());
        }
        assertTrue(ruleNames.contains("A3 - Tip tela - obrnuti trougao"));
        assertTrue(ruleNames.contains("D - Dodavanje bodova za materijal"));
        assertTrue(ruleNames.contains("E2 - Biranje boje ako dresscode utice"));
        assertEquals(1, preporuceniKomadi.getPreporuceneJakne().size() );
        assertEquals(Double.valueOf(70), preporuceniKomadi.getPreporuceneJakne().get(0).getPoeni());

        kieSession.dispose();
    }


    @Test
    public void TestFewClothingItems(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("jaknaPersRulesSession");

        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);

        kieSession.insert(user);
        //kieSession.insert(new GornjiDeo(Boja.BELA, Materijal.PAMUK,0,Vreme.SUVO,  1, "", OdecaPodTip.SIROKA, GornjiDeoEnum.MAJICA_KRATKI));
        for(Jakna jakna : createJakne()){
            kieSession.insert(jakna);
        }
        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();
        kieSession.insert(preporuceniKomadi);
        kieSession.insert(createUnos());
        kieSession.fireAllRules();

        List<Match> activations = agendaEventListener.getMatchList();
        List<String> ruleNames = new ArrayList<>();
        for (Match m : activations) {
            ruleNames.add(m.getRule().getName());
        }

        assertTrue(ruleNames.contains("A3 - Tip tela - obrnuti trougao"));
        assertTrue(ruleNames.contains("C - Dodavanje bodova za tip odece"));
        assertTrue(ruleNames.contains("E1 - Biranje boje odece ako dresscode ne utice"));
        assertTrue(ruleNames.contains("D1 - 2 - Biranje materijala prema tipu odece - kaput, bunda, monton, kardigan"));

        assertEquals(2, preporuceniKomadi.getPreporuceneJakne().size() );

        Optional<PreporucenaJakna> gd = preporuceniKomadi.getPreporuceneJakne().stream().filter(element -> element.getJakna().getId().equals(1L)).findFirst();
        assertEquals(Double.valueOf(106), gd.get().getPoeni());
        gd = preporuceniKomadi.getPreporuceneJakne().stream().filter(element -> element.getJakna().getId().equals(4L)).findFirst();
        assertEquals(Double.valueOf(103), gd.get().getPoeni());

        kieSession.dispose();
    }

    public User createUser(Long id){
        return new User(
                id,
                "user",
                "user",
                Pol.ZENSKI,
                TipTela.PRAVOUGAONIK,
                180,
                80,
                80,
                70,
                80,
                BojaKoze.SPRING,
                new ArrayList<KomadOdece>());
    }

    public UnosDTO createUnos(){
        return new UnosDTO(15, Vreme.SUVO, "Beograd", DressCode.LEZERAN, new ArrayList<Boja>() {{add(Boja.CRVENA); add(Boja.BELA);}});
    }

    public UnosDTO createUnosBlackTie(){
        return new UnosDTO(25, Vreme.SUVO, "Beograd", DressCode.BLACKTIE, new ArrayList<Boja>() {{add(Boja.CRVENA); add(Boja.BELA);}});
    }

    public List<Jakna> createJakne(){
        List<Jakna> jaknaList = new ArrayList<>();
        jaknaList.add(new Jakna(1L, Boja.BELA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", JaknaEnum.JAKNA_PRELAZNI ,OdecaPodTip.SIROKA));
        jaknaList.add(new Jakna(2L, Boja.CRNA, Materijal.POLIESTER, Vreme.VLAZNO, 0,  1, "", JaknaEnum.TRENERKA ,OdecaPodTip.USKA));
        jaknaList.add(new Jakna(3L, Boja.LJUBICASTA, Materijal.PLIS, Vreme.SUVO, 0,  1, "", JaknaEnum.KARDIGAN ,OdecaPodTip.SIROKA));
        jaknaList.add(new Jakna(4L, Boja.ROZA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", JaknaEnum.KAPUT ,OdecaPodTip.USKA));
        return jaknaList;
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
    }
}
