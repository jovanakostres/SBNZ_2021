package com.ftn.PreporukaOdevneKombinacije.service;

import com.ftn.PreporukaOdevneKombinacije.dto.*;
import com.ftn.PreporukaOdevneKombinacije.model.*;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PodaciIzvestaj;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.*;
import com.ftn.PreporukaOdevneKombinacije.model.event.IzabranKomadOdeceEvent;
import com.ftn.PreporukaOdevneKombinacije.model.event.IzabranaKombinacijaEvent;
import com.ftn.PreporukaOdevneKombinacije.model.event.OdbijenKomadEvent;
import com.ftn.PreporukaOdevneKombinacije.repository.KomadOdeceRepository;
import com.ftn.PreporukaOdevneKombinacije.repository.UserRepository;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class KomadOdeceService {

    @Autowired
    private KomadOdeceRepository repository;

    @Autowired
    private GornjiDeoService gornjiDeoService;

    @Autowired
    private DonjiDeoService donjiDeoService;

    @Autowired
    private JaknaService jaknaService;

    @Autowired
    private ObucaService obucaService;

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    @Qualifier(value = "cepOdbijen")
    private KieSession cepOdbijenKomad;

    @Autowired
    @Qualifier(value = "cepIzvestajRulesSession")
    private KieSession cepIzvestaj;

    @Autowired
    @Qualifier(value = "cepIzvestajKombRulesSession")
    private KieSession cepIzvestajKomb;


    public KomadOdece findOne(Long id) {
        return repository.findById(id).orElse(null);
    }
    public KomadOdece create(KomadOdece komadOdece){
        return repository.save(komadOdece);
    }

    @Autowired
    private UserRepository userRepository;

    public PreporuceniKomadi getPreporukaPersonalizovano(UnosDTO unosDTO, User user) {
        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        List<GornjiDeo> gornjiDeoList = new ArrayList<>();
        List<DonjiDeo> donjiDeoList = new ArrayList<>();
        List<Jakna> jaknaList = new ArrayList<>();
        List<Obuca> obucaList = new ArrayList<>();

        for (KomadOdece komadOdece : user.getKomadi()){
            if (komadOdece instanceof GornjiDeo && komadOdece.isAktivan())
                gornjiDeoList.add((GornjiDeo) komadOdece);
            if (komadOdece instanceof DonjiDeo && komadOdece.isAktivan())
                donjiDeoList.add((DonjiDeo) komadOdece);
            if (komadOdece instanceof Jakna && komadOdece.isAktivan())
                jaknaList.add((Jakna) komadOdece );
            if (komadOdece instanceof Obuca && komadOdece.isAktivan())
                obucaList.add((Obuca) komadOdece);
        }

//        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + unosDTO.getMesto() + "&appid=8a45d678f270270943ef5016b28f55e3&units=metric";
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(url)).openConnection()).getInputStream(), Charset.forName("UTF-8")));
//            String resenje = reader.readLine();
//            reader.close();
//            JsonObject jsonObject = new JsonParser().parse(resenje).getAsJsonObject();
//            float temp = Float.parseFloat(jsonObject.getAsJsonObject("main").get("temp").toString());
//            JsonArray jArray =  jsonObject.getAsJsonArray("weather");//getAsJsonObject("symbol");
//            String weather = jArray.get(0).getAsJsonObject().get("main").toString();
//            unosDTO.setTemperatura(temp);
//            if(weather.equals("Thunderstorm") || weather.equals("Drizzle") || weather.equals("Rain") || weather.equals("Snow"))
//                unosDTO.setVreme(Vreme.VLAZNO);
//            else
//                unosDTO.setVreme(Vreme.SUVO);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //vreme end

        preporuceniKomadi = gornjiDeoService.getPreporuceniGornjiDeo(unosDTO,user,gornjiDeoList, preporuceniKomadi);
        preporuceniKomadi = donjiDeoService.getPreporuceniDonjiDeo(unosDTO,user,donjiDeoList, preporuceniKomadi);
        preporuceniKomadi = jaknaService.getPreporucenaJakna(unosDTO,user,jaknaList, preporuceniKomadi);
        preporuceniKomadi = obucaService.getPreporuceniDonjiDeo(unosDTO,user,obucaList, preporuceniKomadi);

        return preporuceniKomadi;
    }

    public void odbijenKomad(KomadOdece komadOdece) throws InterruptedException {

//        KieSessionConfiguration config = KieServices.Factory.get().newKieSessionConfiguration();
//        config.setOption( ClockTypeOption.get("realtime") );
//        KieSession session = kieContainer.newKieSession( "cepOdbijenRulesSession", null, config);

        cepOdbijenKomad.getAgenda().getAgendaGroup( "deaktiviranje" ).setFocus();

        cepOdbijenKomad.insert(new OdbijenKomadEvent(komadOdece));
        cepOdbijenKomad.insert(komadOdece);

        cepOdbijenKomad.fireAllRules();

//        Thread.sleep(2000L);
//
        System.out.println(komadOdece.isAktivan());

//
//        if(!komadOdece.isAktivan()){
//            repository.save(komadOdece);
//            //1 209 600 000
////            Thread.sleep(1209600000);
//            Thread.sleep(2000L);
//            cepOdbijenKomad.getAgenda().getAgendaGroup( "aktiviranje" ).setFocus();
//            cepOdbijenKomad.fireAllRules();
//            System.out.println(komadOdece.isAktivan());
//
//            repository.save(komadOdece);
////            cepOdbijenKomad.dispose();
//        }

        repository.save(komadOdece);
    }

    public void checkNeaktivnost() {

        ArrayList<KomadOdece> neaktivniKomadi = (ArrayList<KomadOdece>) repository.findByAktivan(false);

        if(neaktivniKomadi.size() > 0) {
            for (KomadOdece komadOdece : neaktivniKomadi) {
                cepOdbijenKomad.getAgenda().getAgendaGroup( "aktiviranje" ).setFocus();
                cepOdbijenKomad.insert(komadOdece);

                cepOdbijenKomad.fireAllRules();

                System.out.println(komadOdece.isAktivan());

                repository.save(komadOdece);


            }
        }



    }

    public void izabraniKomadi(IzabranoDTO izabranoDTO) {

        cepIzvestaj.getAgenda().getAgendaGroup( "7danaNajvise" ).setFocus();

        if(izabranoDTO.getIdGornjiDeo() != -1){
            KomadOdece gornjiDeo = findOne(izabranoDTO.getIdGornjiDeo());
            gornjiDeo.setKoeficijentOdabira(gornjiDeo.getKoeficijentOdabira() + gornjiDeo.getKoeficijentOdabira() * 3/100);
            cepIzvestaj.insert(new IzabranKomadOdeceEvent(gornjiDeo, String.valueOf(ZonedDateTime.now().toInstant().toEpochMilli())));
            repository.save(gornjiDeo);
        }
        if(izabranoDTO.getIdDonjiDeo() != -1){
            KomadOdece donjiDeo = findOne(izabranoDTO.getIdDonjiDeo());
            donjiDeo.setKoeficijentOdabira(donjiDeo.getKoeficijentOdabira() + donjiDeo.getKoeficijentOdabira() * 3/100);
            cepIzvestaj.insert(new IzabranKomadOdeceEvent(donjiDeo, String.valueOf(ZonedDateTime.now().toInstant().toEpochMilli())));
            repository.save(donjiDeo);
        }
        if(izabranoDTO.getIdJakna() != -1){
            KomadOdece jakna = findOne(izabranoDTO.getIdJakna());
            jakna.setKoeficijentOdabira(jakna.getKoeficijentOdabira() + jakna.getKoeficijentOdabira() * 3/100);
            cepIzvestaj.insert(new IzabranKomadOdeceEvent(jakna, String.valueOf(ZonedDateTime.now().toInstant().toEpochMilli())));
            repository.save(jakna);
        }
        if(izabranoDTO.getIdObuca() != -1){
            KomadOdece obuca = findOne(izabranoDTO.getIdObuca());
            obuca.setKoeficijentOdabira(obuca.getKoeficijentOdabira() + obuca.getKoeficijentOdabira() * 3/100);
            cepIzvestaj.insert(new IzabranKomadOdeceEvent(obuca, String.valueOf(ZonedDateTime.now().toInstant().toEpochMilli())));
            repository.save(obuca);
        }

        cepIzvestaj.fireAllRules();

    }

    public void izabraniKomadiKomb(IzabranoDTO izabranoDTO) {

        if(izabranoDTO.getIdGornjiDeo() != -1){
            if (izabranoDTO.getIdDonjiDeo() != -1){
                cepIzvestajKomb.insert(new IzabranaKombinacijaEvent(findOne(izabranoDTO.getIdGornjiDeo()), findOne(izabranoDTO.getIdDonjiDeo())));
            }
            if (izabranoDTO.getIdJakna() != -1){
                cepIzvestajKomb.insert(new IzabranaKombinacijaEvent(findOne(izabranoDTO.getIdGornjiDeo()), findOne(izabranoDTO.getIdJakna())));
            }
            if (izabranoDTO.getIdObuca() != -1){
                cepIzvestajKomb.insert(new IzabranaKombinacijaEvent(findOne(izabranoDTO.getIdGornjiDeo()), findOne(izabranoDTO.getIdObuca())));
            }
        }
        if(izabranoDTO.getIdDonjiDeo() != -1){
            if (izabranoDTO.getIdJakna() != -1){
                cepIzvestajKomb.insert(new IzabranaKombinacijaEvent(findOne(izabranoDTO.getIdDonjiDeo()), findOne(izabranoDTO.getIdJakna())));
            }
            if (izabranoDTO.getIdObuca() != -1){
                cepIzvestajKomb.insert(new IzabranaKombinacijaEvent(findOne(izabranoDTO.getIdDonjiDeo()), findOne(izabranoDTO.getIdObuca())));
            }
        }
        if(izabranoDTO.getIdJakna() != -1){
            if (izabranoDTO.getIdObuca() != -1){
                cepIzvestajKomb.insert(new IzabranaKombinacijaEvent(findOne(izabranoDTO.getIdJakna()), findOne(izabranoDTO.getIdObuca())));
            }
        }

        cepIzvestajKomb.fireAllRules();

    }

    public PodaciIzvestaj get24Report(User userDetails) {

        cepIzvestaj.getAgenda().getAgendaGroup( "24sataSvePreporucivano" ).setFocus();

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();
        VremeDTO vremeDTO = new VremeDTO();
        cepIzvestaj.insert(userDetails);
        cepIzvestaj.insert(preporuceniKomadi);
        cepIzvestaj.insert(vremeDTO);

        cepIzvestaj.fireAllRules();

        return new PodaciIzvestaj(preporuceniKomadi, vremeDTO);

    }

    public PodaciIzvestaj get7Report(User userDetails) {

        cepIzvestaj.getAgenda().getAgendaGroup( "7danaSvePreporucivano" ).setFocus();

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();
        VremeDTO vremeDTO = new VremeDTO();
        cepIzvestaj.insert(userDetails);
        cepIzvestaj.insert(preporuceniKomadi);
        cepIzvestaj.insert(vremeDTO);

        cepIzvestaj.fireAllRules();

        return new PodaciIzvestaj(preporuceniKomadi, vremeDTO);

    }

    public PreporuceniKomadi get7Most(User userDetails) {

        cepIzvestaj.getAgenda().getAgendaGroup( "7danaNajvise" ).setFocus();

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();
        cepIzvestaj.insert(userDetails);
        cepIzvestaj.insert(preporuceniKomadi);

        cepIzvestaj.fireAllRules();

        return preporuceniKomadi;

    }


    public PreporuceniKomadi getMostComb(Long id) {

//        cepIzvestaj.getAgenda().getAgendaGroup( "7danaNajvise" ).setFocus();

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();
        cepIzvestajKomb.insert(findOne(id));
        cepIzvestajKomb.insert(preporuceniKomadi);

        cepIzvestajKomb.fireAllRules();

        return preporuceniKomadi;

    }

    public void deleteListPreporucenihKomb() {

        cepIzvestajKomb.getAgenda().getAgendaGroup( "brisanjeListeKomb" ).setFocus();

        cepIzvestajKomb.fireAllRules();

    }

    public void deleteListPreporucenih() {

        cepIzvestaj.getAgenda().getAgendaGroup( "brisanjeListe" ).setFocus();

        cepIzvestaj.fireAllRules();

    }


    public PreporuceniKomadi getPreporukaOpste(UnosNeulogovanDTO unosDTO) {

        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        List<GornjiDeo> gornjiDeoList = new ArrayList<>();
        List<DonjiDeo> donjiDeoList = new ArrayList<>();
        List<Jakna> jaknaList = new ArrayList<>();
        List<Obuca> obucaList = new ArrayList<>();

        for (KomadOdece komadOdece : getKomadiById()){
            if(komadOdece.getPol()!= unosDTO.getPol())
                continue;
            if (komadOdece instanceof GornjiDeo)
                gornjiDeoList.add((GornjiDeo) komadOdece);
            if (komadOdece instanceof DonjiDeo)
                donjiDeoList.add((DonjiDeo) komadOdece);
            if (komadOdece instanceof Jakna)
                jaknaList.add((Jakna) komadOdece);
            if (komadOdece instanceof Obuca)
                obucaList.add((Obuca) komadOdece);
        }

        System.out.println(gornjiDeoList.size());
        preporuceniKomadi = gornjiDeoService.getPreporuceniGornjiDeoOpste(unosDTO,gornjiDeoList,preporuceniKomadi);
        System.out.println(donjiDeoList.size());
        preporuceniKomadi = donjiDeoService.getPreporuceniDonjiDeoOpste(unosDTO,donjiDeoList,preporuceniKomadi);
        System.out.println(jaknaList.size());
        preporuceniKomadi = jaknaService.getPreporucenaJaknaOpste(unosDTO,jaknaList,preporuceniKomadi);
        preporuceniKomadi = obucaService.getPreporuceniObuca(unosDTO,obucaList, preporuceniKomadi);

        return preporuceniKomadi;
    }

    private Iterable<? extends KomadOdece> getKomadiById() {
        User user = userRepository.findById(2L).orElseGet(null);
        return user.getKomadi();

    }

	public void addKomad(KomadOdece ko) {
        repository.save(ko);
    }

    public List<KomadOdece> findAll() {
        return repository.findAll();
    }

    public List<KomadOdece> filterOdeca(FilterDTO filterDTO, User user) {
        KieSession queryFilter = kieContainer.newKieSession("queryKomadOdeceRulesSession");

        List<KomadOdece> komadOdeces = user.getKomadi();

        Class klasa;

        switch (filterDTO.getDeo()) {
            case "GORNJIDEO":
                klasa = GornjiDeo.class;
                break;
            case "DONJIDEO":
                klasa = DonjiDeo.class;
                break;
            case "JAKNA":
                klasa = Jakna.class;
                break;
            case "OBUCA":
                klasa = Obuca.class;
                break;
            default:
                klasa = KomadOdece.class;
        }

        if(!filterDTO.getDeo().equals("SVE")) {
            for (KomadOdece komadOdece : komadOdeces) {
                queryFilter.insert(komadOdece);
            }
            QueryResults results = queryFilter.getQueryResults("getDelovi", klasa);
            komadOdeces = new ArrayList<>();
            for (QueryResultsRow resultsRow : results) {
                KomadOdece komadOdece = (KomadOdece) resultsRow.get("$komad");
                komadOdeces.add(komadOdece);
            }
        }

        queryFilter.fireAllRules();

        if(!filterDTO.getBoja().equals("SVE")) {
            for (KomadOdece komadOdece : komadOdeces) {
                queryFilter.insert(komadOdece);
            }
            QueryResults results = queryFilter.getQueryResults("getKomadOdeceByBoja", Boja.valueOf(filterDTO.getBoja()));
            komadOdeces = new ArrayList<>();
            for (QueryResultsRow resultsRow : results) {
                KomadOdece komadOdece = (KomadOdece) resultsRow.get("$komad");
                komadOdeces.add(komadOdece);
            }
        }

        queryFilter.fireAllRules();

        if(!filterDTO.getMaterijal().equals("SVE")) {
            for (KomadOdece komadOdece : komadOdeces) {
                queryFilter.insert(komadOdece);
            }
            QueryResults results = queryFilter.getQueryResults("getKomadOdeceByMaterijal", Materijal.valueOf(filterDTO.getMaterijal()));
            komadOdeces = new ArrayList<>();
            for (QueryResultsRow resultsRow : results) {
                KomadOdece komadOdece = (KomadOdece) resultsRow.get("$komad");
                komadOdeces.add(komadOdece);
            }
        }

        queryFilter.fireAllRules();

        if(!filterDTO.getTip().equals("SVE")) {
            for (KomadOdece komadOdece : komadOdeces) {
                queryFilter.insert(komadOdece);
            }
            QueryResults results = null;
            switch (filterDTO.getDeo()) {
                case "DONJIDEO":
                    results = queryFilter.getQueryResults("getDonjiDeoByTip", DonjiDeoEnum.valueOf(filterDTO.getTip()));
                    break;
                case "JAKNA":
                    results = queryFilter.getQueryResults("getJaknaByTip", JaknaEnum.valueOf(filterDTO.getTip()));
                    break;
                case "OBUCA":
                    results = queryFilter.getQueryResults("getObucaByTip", ObucaEnum.valueOf(filterDTO.getTip()));
                    break;
                default:
                    results = queryFilter.getQueryResults("getGornjiDeoByTip", GornjiDeoEnum.valueOf(filterDTO.getTip()));
            }

            komadOdeces = new ArrayList<>();
            for (QueryResultsRow resultsRow : results) {
                KomadOdece komadOdece = (KomadOdece) resultsRow.get("$komad");
                komadOdeces.add(komadOdece);
            }
        }

        queryFilter.fireAllRules();

        if(!filterDTO.getPodtip().equals("SVE")) {
            for (KomadOdece komadOdece : komadOdeces) {
                queryFilter.insert(komadOdece);
            }
            QueryResults results = null;
            switch (filterDTO.getDeo()) {
                case "DONJIDEO":
                    results = queryFilter.getQueryResults("getDonjiDeoByPodTip", OdecaPodTip.valueOf(filterDTO.getPodtip()));
                    break;
                case "JAKNA":
                    results = queryFilter.getQueryResults("getJaknaByPodTip", OdecaPodTip.valueOf(filterDTO.getPodtip()));
                    break;
                default:
                    results = queryFilter.getQueryResults("getGornjiDeoByPodTip", OdecaPodTip.valueOf(filterDTO.getPodtip()));
            }

            komadOdeces = new ArrayList<>();
            for (QueryResultsRow resultsRow : results) {
                KomadOdece komadOdece = (KomadOdece) resultsRow.get("$komad");
                komadOdeces.add(komadOdece);
            }
        }

        queryFilter.dispose();

        return komadOdeces;
    }
}
