package com.ftn.PreporukaOdevneKombinacije.service;

import com.ftn.PreporukaOdevneKombinacije.dto.IzabranoDTO;
import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.dto.UnosNeulogovanDTO;
import com.ftn.PreporukaOdevneKombinacije.dto.VremeDTO;
import com.ftn.PreporukaOdevneKombinacije.model.*;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PodaciIzvestaj;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Pol;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Vreme;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

        new Thread(new Runnable() {
            public void run() {
                cepOdbijenKomad.fireAllRules();
            }
        }).start();

        Thread.sleep(2000L);

        System.out.println(komadOdece.isAktivan());

        if(!komadOdece.isAktivan()){
            repository.save(komadOdece);
            Thread.sleep(2000L);
            cepOdbijenKomad.getAgenda().getAgendaGroup( "aktiviranje" ).setFocus();
            cepOdbijenKomad.fireAllRules();
            System.out.println(komadOdece.isAktivan());

            repository.save(komadOdece);
//            cepOdbijenKomad.dispose();
        }

        repository.save(komadOdece);


    }

//    public void saveIzabrano(IzabranoDTO izabranoDTO) {
//        KieSession kieSession = kieContainer.newKieSession("gDPersRulesSession");
//        kieSession.insert(new IzabranKomadOdeceEvent(gornjiDeoService.findOne(izabranoDTO.getIdGornjiDeo())));
//        kieSession.insert(new IzabranKomadOdeceEvent(donjiDeoService.findOne(izabranoDTO.getIdDonjiDeo())));
//        kieSession.insert(new IzabranKomadOdeceEvent(jaknaService.findOne(izabranoDTO.getIdJakna())));
//        kieSession.insert(new IzabranKomadOdeceEvent(obucaService.findOne(izabranoDTO.getIdObuca())));
//
//        kieSession.fireAllRules();
//    }

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
        //preporuceniKomadi = obucaService.getPreporuceniDonjiDeo(unosDTO,user,obucaList, preporuceniKomadi);

        return preporuceniKomadi;
    }

    private Iterable<? extends KomadOdece> getKomadiById() {
        User user = userRepository.findById(2L).orElseGet(null);
        return user.getKomadi();

    }

	public void addKomad(KomadOdece ko) {
        repository.save(ko);
    }

}
