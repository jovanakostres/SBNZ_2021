package com.ftn.PreporukaOdevneKombinacije;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.helper.TrackingAgendaEventListener;
import com.ftn.PreporukaOdevneKombinacije.model.Jakna;
import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import com.ftn.PreporukaOdevneKombinacije.model.Obuca;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporucenaJakna;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporucenaObuca;
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

public class ObucaPersonalizovanoTest {

    @Test
    public void TestNoClothingItems(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("obucaPersRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        kieSession.insert(createUnos());
        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

        assertEquals(0, preporuceniKomadi.getPreporucenaObuca().size() );

        kieSession.dispose();
    }

    @Test
    public void TestOneClothingItems(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("obucaPersRulesSession");
        kieSession.insert(user);
        kieSession.insert(new Obuca(Boja.BELA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", true, ObucaEnum.CIZME ));

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        kieSession.insert(createUnos());
        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

        assertEquals(1, preporuceniKomadi.getPreporucenaObuca().size() );
        assertEquals(Double.valueOf(61), preporuceniKomadi.getPreporucenaObuca().get(0).getPoeni());

        kieSession.dispose();
    }

    @Test
    public void TestOneClothingItemsDresscodeBlackTie(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("obucaPersRulesSession");
        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);

        kieSession.insert(user);
        kieSession.insert(new Obuca(1L, Boja.BELA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", true, ObucaEnum.CIZME));

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        kieSession.insert(createUnosBlackTie());
        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

        List<Match> activations = agendaEventListener.getMatchList();
        List<String> ruleNames = new ArrayList<>();
        for (Match m : activations) {
            ruleNames.add(m.getRule().getName());
        }
        assertTrue(ruleNames.contains("A3 - Tip tela - obrnuti trougao"));
        assertTrue(ruleNames.contains("D - Dodavanje bodova za materijal"));
        assertTrue(ruleNames.contains("E2 - Biranje boje ako dresscode utice"));
        assertEquals(1, preporuceniKomadi.getPreporucenaObuca().size() );
        assertEquals(Double.valueOf(84), preporuceniKomadi.getPreporucenaObuca().get(0).getPoeni());

        kieSession.dispose();
    }


    @Test
    public void TestFewClothingItems(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("obucaPersRulesSession");

        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);

        kieSession.insert(user);
        //kieSession.insert(new GornjiDeo(Boja.BELA, Materijal.PAMUK,0,Vreme.SUVO,  1, "", OdecaPodTip.SIROKA, GornjiDeoEnum.MAJICA_KRATKI));
        for(Obuca obuca : createObuca()){
            kieSession.insert(obuca);
        }
        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        kieSession.insert(createUnos());
        kieSession.fireAllRules();
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();

        List<Match> activations = agendaEventListener.getMatchList();
        List<String> ruleNames = new ArrayList<>();
        for (Match m : activations) {
            ruleNames.add(m.getRule().getName());
        }

        assertTrue(ruleNames.contains("A3 - Tip tela - obrnuti trougao"));
        assertTrue(ruleNames.contains("C - Dodavanje bodova za tip obuce"));
        assertTrue(ruleNames.contains("E1 - Biranje boje obuce ako dresscode ne utice"));
        assertTrue(ruleNames.contains("D1 - 2 - Biranje materijala prema tipu obuce - cizme, cipele"));

        assertEquals(2, preporuceniKomadi.getPreporucenaObuca().size() );

        Optional<PreporucenaObuca> gd = preporuceniKomadi.getPreporucenaObuca().stream().filter(element -> element.getObuca().getId().equals(1L)).findFirst();
        assertEquals(Double.valueOf(67), gd.get().getPoeni());
        gd = preporuceniKomadi.getPreporucenaObuca().stream().filter(element -> element.getObuca().getId().equals(4L)).findFirst();
        assertEquals(Double.valueOf(62), gd.get().getPoeni());

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

    public List<Obuca> createObuca(){
        List<Obuca> obucaList = new ArrayList<>();
        obucaList.add(new Obuca(1L, Boja.BELA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", true, ObucaEnum.CIZME));
        obucaList.add(new Obuca(2L, Boja.CRNA, Materijal.POLIESTER, Vreme.SUVO, 0,  1, "", true, ObucaEnum.SANDALE));
        obucaList.add(new Obuca(3L, Boja.BRAON, Materijal.KOZA, Vreme.SUVO, 0,  1, "", true, ObucaEnum.CIPELE));
        obucaList.add(new Obuca(4L, Boja.PLAVA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", true, ObucaEnum.PATIKE));
        return obucaList;
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
}
