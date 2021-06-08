package com.ftn.PreporukaOdevneKombinacije;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.model.*;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.*;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QueryPersTests {

    @Test
    public void TestQueryMaterijal(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("queryKomadOdeceRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        for(KomadOdece komadOdece : createKomadeOdece(user)){
            kieSession.insert(komadOdece);
        }
        QueryResults results = kieSession.getQueryResults("getKomadOdeceByMaterijal", Materijal.PAMUK, user.getId());
        for(QueryResultsRow resultsRow : results){
            KomadOdece komadOdece = (KomadOdece) resultsRow.get("$komad");
            System.out.println(komadOdece.getId());
        }

        assertEquals(3, results.size() );

        kieSession.dispose();
    }

    @Test
    public void TestQueryBoja(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("queryKomadOdeceRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        for(KomadOdece komadOdece : createKomadeOdece(user)){
            kieSession.insert(komadOdece);
        }
        QueryResults results = kieSession.getQueryResults("getKomadOdeceByBoja", Boja.BELA, user.getId());
        for(QueryResultsRow resultsRow : results){
            KomadOdece komadOdece = (KomadOdece) resultsRow.get("$komad");
            System.out.println(komadOdece.getId());
        }

        assertEquals(3, results.size() );

        kieSession.dispose();
    }

    @Test
    public void TestQueryGornjiDeoNoTip(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("queryKomadOdeceRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        for(KomadOdece komadOdece : createKomadeOdece(user)){
            kieSession.insert(komadOdece);
        }
        QueryResults results = kieSession.getQueryResults("getGornjiDeoByTip", GornjiDeoEnum.DZEMPER, user.getId());
        for(QueryResultsRow resultsRow : results){
            GornjiDeo komadOdece = (GornjiDeo) resultsRow.get("$komad");
            System.out.println(komadOdece.getId());
        }

        assertEquals(0, results.size() );

        kieSession.dispose();
    }

    @Test
    public void TestQueryGornjiDeoTip(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("queryKomadOdeceRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        for(KomadOdece komadOdece : createKomadeOdece(user)){
            kieSession.insert(komadOdece);
        }
        QueryResults results = kieSession.getQueryResults("getGornjiDeoByTip", GornjiDeoEnum.DUKS, user.getId());
        for(QueryResultsRow resultsRow : results){
            GornjiDeo komadOdece = (GornjiDeo) resultsRow.get("$komad");
            System.out.println(komadOdece.getId());
        }

        assertEquals(1, results.size() );

        kieSession.dispose();
    }

    @Test
    public void TestQueryDonjiDeoNoTip(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("queryKomadOdeceRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        for(KomadOdece komadOdece : createKomadeOdece(user)){
            kieSession.insert(komadOdece);
        }
        QueryResults results = kieSession.getQueryResults("getDonjiDeoByTip", DonjiDeoEnum.HELANKE, user.getId());
        for(QueryResultsRow resultsRow : results){
            DonjiDeo komadOdece = (DonjiDeo) resultsRow.get("$komad");
            System.out.println(komadOdece.getId());
        }

        assertEquals(0, results.size() );

        kieSession.dispose();
    }

    @Test
    public void TestQueryDonjiDeoTip(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("queryKomadOdeceRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        for(KomadOdece komadOdece : createKomadeOdece(user)){
            kieSession.insert(komadOdece);
        }
        QueryResults results = kieSession.getQueryResults("getDonjiDeoByTip", DonjiDeoEnum.PANTALONE, user.getId());
        for(QueryResultsRow resultsRow : results){
            DonjiDeo komadOdece = (DonjiDeo) resultsRow.get("$komad");
            System.out.println(komadOdece.getId());
        }

        assertEquals(1, results.size() );

        kieSession.dispose();
    }

    @Test
    public void TestQueryJaknaNoTip(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("queryKomadOdeceRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        for(KomadOdece komadOdece : createKomadeOdece(user)){
            kieSession.insert(komadOdece);
        }
        QueryResults results = kieSession.getQueryResults("getJaknaByTip", JaknaEnum.BUNDA, user.getId());
        for(QueryResultsRow resultsRow : results){
            Jakna komadOdece = (Jakna) resultsRow.get("$komad");
            System.out.println(komadOdece.getId());
        }

        assertEquals(0, results.size() );

        kieSession.dispose();
    }

    @Test
    public void TestQueryJaknaTip(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("queryKomadOdeceRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        for(KomadOdece komadOdece : createKomadeOdece(user)){
            kieSession.insert(komadOdece);
        }
        QueryResults results = kieSession.getQueryResults("getJaknaByTip", JaknaEnum.JAKNA_PRELAZNI, user.getId());
        for(QueryResultsRow resultsRow : results){
            Jakna komadOdece = (Jakna) resultsRow.get("$komad");
            System.out.println(komadOdece.getId());
        }

        assertEquals(1, results.size() );

        kieSession.dispose();
    }

    @Test
    public void TestQueryGornjiDeoNoPodTip(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("queryKomadOdeceRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        for(KomadOdece komadOdece : createKomadeOdece(user)){
            kieSession.insert(komadOdece);
        }
        QueryResults results = kieSession.getQueryResults("getGornjiDeoByPodTip", OdecaPodTip.SIROKA, user.getId());
        for(QueryResultsRow resultsRow : results){
            GornjiDeo komadOdece = (GornjiDeo) resultsRow.get("$komad");
            System.out.println(komadOdece.getId());
        }

        assertEquals(0, results.size() );

        kieSession.dispose();
    }

    @Test
    public void TestQueryGornjiDeoPodTip(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("queryKomadOdeceRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        for(KomadOdece komadOdece : createKomadeOdece(user)){
            kieSession.insert(komadOdece);
        }
        QueryResults results = kieSession.getQueryResults("getGornjiDeoByPodTip", OdecaPodTip.USKA, user.getId());
        for(QueryResultsRow resultsRow : results){
            GornjiDeo komadOdece = (GornjiDeo) resultsRow.get("$komad");
            System.out.println(komadOdece.getId());
        }

        assertEquals(1, results.size() );

        kieSession.dispose();
    }

    @Test
    public void TestQueryDonjiDeoNoPodTip(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("queryKomadOdeceRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        for(KomadOdece komadOdece : createKomadeOdece(user)){
            kieSession.insert(komadOdece);
        }
        QueryResults results = kieSession.getQueryResults("getDonjiDeoByPodTip", OdecaPodTip.USKA, user.getId());
        for(QueryResultsRow resultsRow : results){
            DonjiDeo komadOdece = (DonjiDeo) resultsRow.get("$komad");
            System.out.println(komadOdece.getId());
        }

        assertEquals(0, results.size() );

        kieSession.dispose();
    }

    @Test
    public void TestQueryDonjiDeoPodTip(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("queryKomadOdeceRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        for(KomadOdece komadOdece : createKomadeOdece(user)){
            kieSession.insert(komadOdece);
        }
        QueryResults results = kieSession.getQueryResults("getDonjiDeoByPodTip", OdecaPodTip.SIROKA, user.getId());
        for(QueryResultsRow resultsRow : results){
            DonjiDeo komadOdece = (DonjiDeo) resultsRow.get("$komad");
            System.out.println(komadOdece.getId());
        }

        assertEquals(1, results.size() );

        kieSession.dispose();
    }

    @Test
    public void TestQueryJaknaNoPodTip(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("queryKomadOdeceRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        for(KomadOdece komadOdece : createKomadeOdece(user)){
            kieSession.insert(komadOdece);
        }
        QueryResults results = kieSession.getQueryResults("getJaknaByPodTip", OdecaPodTip.USKA, user.getId());
        for(QueryResultsRow resultsRow : results){
            Jakna komadOdece = (Jakna) resultsRow.get("$komad");
            System.out.println(komadOdece.getId());
        }

        assertEquals(0, results.size() );

        kieSession.dispose();
    }

    @Test
    public void TestQueryJaknaPodTip(){
        User user = createUser(1L);
        KieContainer kieContainer = KieServices.Factory.get().getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("queryKomadOdeceRulesSession");
        kieSession.insert(user);

        insertTipTela(kieSession);
        insertPodTip(kieSession);
        insertVreme(kieSession);
        insertDresscode(kieSession);
        insertMaterijal(kieSession);
        insertOdecaTip(kieSession);

        for(KomadOdece komadOdece : createKomadeOdece(user)){
            kieSession.insert(komadOdece);
        }
        QueryResults results = kieSession.getQueryResults("getJaknaByPodTip", OdecaPodTip.SIROKA, user.getId());
        for(QueryResultsRow resultsRow : results){
            Jakna komadOdece = (Jakna) resultsRow.get("$komad");
            System.out.println(komadOdece.getId());
        }

        assertEquals(1, results.size() );

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

    public List<KomadOdece> createKomadeOdece(User korisnik){
        List<KomadOdece> donjiDeoList = new ArrayList<>();
        donjiDeoList.add(new DonjiDeo(1L, Boja.BELA, Materijal.TEKSAS, Vreme.SUVO, 0,  1, "", korisnik, true, DonjiDeoEnum.PANTALONE ,OdecaPodTip.SIROKA));
        donjiDeoList.add(new GornjiDeo(2L, Boja.CRNA, Materijal.PAMUK,0,Vreme.VLAZNO,  1, "", korisnik, true, OdecaPodTip.USKA, GornjiDeoEnum.DUKS));
        donjiDeoList.add(new Jakna(3L, Boja.BELA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", korisnik, true, JaknaEnum.JAKNA_PRELAZNI ,OdecaPodTip.SIROKA));
        donjiDeoList.add(new Obuca(4L, Boja.BELA, Materijal.PAMUK, Vreme.SUVO, 0,  1, "", korisnik, true, ObucaEnum.CIZME));
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
