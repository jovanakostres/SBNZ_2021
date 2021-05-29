package com.ftn.PreporukaOdevneKombinacije.service;

import com.ftn.PreporukaOdevneKombinacije.dto.UnosDTO;
import com.ftn.PreporukaOdevneKombinacije.model.*;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Vreme;
import com.ftn.PreporukaOdevneKombinacije.repository.KomadOdeceRepository;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
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



    public PreporuceniKomadi getPreporukaPersonalizovano(UnosDTO unosDTO, User user) {
        PreporuceniKomadi preporuceniKomadi = new PreporuceniKomadi();

        List<GornjiDeo> gornjiDeoList = new ArrayList<>();
        List<DonjiDeo> donjiDeoList = new ArrayList<>();
        List<Jakna> jaknaList = new ArrayList<>();
        List<Obuca> obucaList = new ArrayList<>();

        for (KomadOdece komadOdece : user.getKomadi()){
            if (komadOdece instanceof GornjiDeo)
                gornjiDeoList.add((GornjiDeo) komadOdece);
            if (komadOdece instanceof DonjiDeo)
                donjiDeoList.add((DonjiDeo) komadOdece);
            if (komadOdece instanceof Jakna)
                jaknaList.add((Jakna) komadOdece);
            if (komadOdece instanceof Obuca)
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

}
