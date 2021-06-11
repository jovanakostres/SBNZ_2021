package com.ftn.PreporukaOdevneKombinacije;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.helper.TrackingAgendaEventListener;
import com.ftn.PreporukaOdevneKombinacije.model.GornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import com.ftn.PreporukaOdevneKombinacije.model.User;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniGornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.*;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.Match;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GornjiDeoPersonalizovanoTests {

    //vrati prazan niz preporuke kada nema gornjih delova u memoriji
    @Test
    public void TestNoClothingItems(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("gDPersRulesSession");
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

        assertEquals(0, preporuceniKomadi.getPreporuceniGornjiDelovi().size() );

        kieSession.dispose();
    }

    @Test
    public void TestOneClothingItems(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("gDPersRulesSession");
        kieSession.insert(user);
        kieSession.insert(new GornjiDeo(Boja.BELA, Materijal.PAMUK,0,Vreme.SUVO,  1, "", true, OdecaPodTip.SIROKA, GornjiDeoEnum.MAJICA_KRATKI));

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

        assertEquals(1, preporuceniKomadi.getPreporuceniGornjiDelovi().size() );
        assertEquals(Double.valueOf(61), preporuceniKomadi.getPreporuceniGornjiDelovi().get(0).getPoeni());

        kieSession.dispose();
    }

    @Test
    public void TestOneClothingItemsDresscodeBlackTie(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("gDPersRulesSession");
        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);

        kieSession.insert(user);
        kieSession.insert(new GornjiDeo(Boja.BELA, Materijal.PAMUK,0,Vreme.SUVO,  1, "", true, OdecaPodTip.SIROKA, GornjiDeoEnum.KOSULJA));

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
        assertTrue(ruleNames.contains("C - Dodavanje bodova za tip odece"));
        assertTrue(ruleNames.contains("E2 - Biranje boje ako dresscode utice"));
        assertEquals(1, preporuceniKomadi.getPreporuceniGornjiDelovi().size() );
        assertEquals(Double.valueOf(96), preporuceniKomadi.getPreporuceniGornjiDelovi().get(0).getPoeni());

        kieSession.dispose();
    }


    @Test
    public void TestFewClothingItems(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("gDPersRulesSession");

        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);

        kieSession.insert(user);
        //kieSession.insert(new GornjiDeo(Boja.BELA, Materijal.PAMUK,0,Vreme.SUVO,  1, "", OdecaPodTip.SIROKA, GornjiDeoEnum.MAJICA_KRATKI));
        for(GornjiDeo gd : createGornjiDelovi()){
            kieSession.insert(gd);
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
        assertTrue(ruleNames.contains("C - Dodavanje bodova za tip odece"));
        assertTrue(ruleNames.contains("E1 - Biranje boje odece ako dresscode ne utice"));
        assertTrue(ruleNames.contains("D1 - 2 - Biranje materijala prema tipu odece - kosulja, bluza, tunika"));

        assertEquals(3, preporuceniKomadi.getPreporuceniGornjiDelovi().size() );

        Optional<PreporuceniGornjiDeo> gd = preporuceniKomadi.getPreporuceniGornjiDelovi().stream().filter(element -> element.getGornjiDeo().getId().equals(1L)).findFirst();
        assertEquals(Double.valueOf(69), gd.get().getPoeni());
        gd = preporuceniKomadi.getPreporuceniGornjiDelovi().stream().filter(element -> element.getGornjiDeo().getId().equals(3L)).findFirst();
        assertEquals(Double.valueOf(81), gd.get().getPoeni());
        gd = preporuceniKomadi.getPreporuceniGornjiDelovi().stream().filter(element -> element.getGornjiDeo().getId().equals(2L)).findFirst();
        assertEquals(Double.valueOf(69), gd.get().getPoeni());

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

    public List<GornjiDeo> createGornjiDelovi(){
        List<GornjiDeo> gornjiDeoList = new ArrayList<>();
        gornjiDeoList.add(new GornjiDeo(1L, Boja.BELA, Materijal.PAMUK,0,Vreme.SUVO,  1, "", true, OdecaPodTip.SIROKA, GornjiDeoEnum.TUNIKA));
        gornjiDeoList.add(new GornjiDeo(2L, Boja.CRNA, Materijal.PAMUK,0,Vreme.VLAZNO,  1, "", true, OdecaPodTip.USKA, GornjiDeoEnum.DUKS));
        gornjiDeoList.add(new GornjiDeo(3L, Boja.CRVENA, Materijal.POLIESTER,0,Vreme.VLAZNO,  1, "", true, OdecaPodTip.USKA, GornjiDeoEnum.MAJICA_DUGI));
        gornjiDeoList.add(new GornjiDeo(4L, Boja.NARANDZASTA, Materijal.TEKSAS,0,Vreme.SUVO,  1, "", true, OdecaPodTip.SIROKA, GornjiDeoEnum.KOSULJA));
        return gornjiDeoList;
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
    }
}
