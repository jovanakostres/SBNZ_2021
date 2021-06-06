package com.ftn.PreporukaOdevneKombinacije;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
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
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DonjiDeoPersonalizovanoTests {

    @Test
    public void TestNoClothingItems(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("dDPersRulesSession");
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

        assertEquals(0, preporuceniKomadi.getPreporuceniDonjiDelovi().size() );

        kieSession.dispose();
    }

    @Test
    public void TestOneClothingItems(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("dDPersRulesSession");
        kieSession.insert(user);
        kieSession.insert(new DonjiDeo(Boja.BELA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", true, DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA));

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

        assertEquals(1, preporuceniKomadi.getPreporuceniDonjiDelovi().size() );
        assertEquals(Double.valueOf(95), preporuceniKomadi.getPreporuceniDonjiDelovi().get(0).getPoeni());

        kieSession.dispose();
    }

    @Test
    public void TestOneClothingItemsDresscodeBlackTie(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("dDPersRulesSession");
        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);

        kieSession.insert(user);
        kieSession.insert(new DonjiDeo(1L, Boja.BELA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", true, DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA));

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
        assertTrue(ruleNames.contains("C - Dodavanje bodova za tip odece"));
        assertTrue(ruleNames.contains("E2 - Biranje boje ako dresscode utice"));
        assertEquals(1, preporuceniKomadi.getPreporuceniDonjiDelovi().size() );
        assertEquals(Double.valueOf(100), preporuceniKomadi.getPreporuceniDonjiDelovi().get(0).getPoeni());

        kieSession.dispose();
    }


    @Test
    public void TestFewClothingItems(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("dDPersRulesSession");

        TrackingAgendaEventListener agendaEventListener = new TrackingAgendaEventListener();
        kieSession.addEventListener(agendaEventListener);

        kieSession.insert(user);
        //kieSession.insert(new GornjiDeo(Boja.BELA, Materijal.PAMUK,0,Vreme.SUVO,  1, "", OdecaPodTip.SIROKA, GornjiDeoEnum.MAJICA_KRATKI));
        for(DonjiDeo dd : createDonjiDelovi()){
            kieSession.insert(dd);
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
        assertTrue(ruleNames.contains("D1 - 1 - Biranje materijala prema tipu odece - kratka suknja, maxi suknja, pantalone i kratke pantalone"));

        assertEquals(2, preporuceniKomadi.getPreporuceniDonjiDelovi().size() );

        Optional<PreporuceniDonjiDeo> gd = preporuceniKomadi.getPreporuceniDonjiDelovi().stream().filter(element -> element.getDonjiDeo().getId().equals(1L)).findFirst();
        assertEquals(Double.valueOf(96), gd.get().getPoeni());
        gd = preporuceniKomadi.getPreporuceniDonjiDelovi().stream().filter(element -> element.getDonjiDeo().getId().equals(4L)).findFirst();
        assertEquals(Double.valueOf(92), gd.get().getPoeni());

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

    public List<DonjiDeo> createDonjiDelovi(){
        List<DonjiDeo> donjiDeoList = new ArrayList<>();
        donjiDeoList.add(new DonjiDeo(1L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", true, DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA));
        donjiDeoList.add(new DonjiDeo(2L, Boja.BRAON, Materijal.SOMOT, Vreme.SUVO, 0,  1, "", true, DonjiDeoEnum.KRATKA_SUKNJA ,OdecaPodTip.USKA));
        donjiDeoList.add(new DonjiDeo(3L, Boja.ZELENA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", true, DonjiDeoEnum.TRENERKA ,OdecaPodTip.SIROKA));
        donjiDeoList.add(new DonjiDeo(4L, Boja.CRNA, Materijal.POLIESTER, Vreme.VLAZNO, 0,  1, "", true, DonjiDeoEnum.HELANKE ,OdecaPodTip.USKA));
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
}
