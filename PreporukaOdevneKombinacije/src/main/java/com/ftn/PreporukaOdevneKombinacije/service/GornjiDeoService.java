package com.ftn.PreporukaOdevneKombinacije.service;

import com.ftn.PreporukaOdevneKombinacije.model.KomadOdece;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniGornjiDeo;
import com.ftn.PreporukaOdevneKombinacije.model.drlModel.PreporuceniKomadi;
import com.ftn.PreporukaOdevneKombinacije.model.enums.DressCode;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GornjiDeoService {

    @Autowired
    private KieContainer kieContainer;

    public PreporuceniKomadi getPreporuceniGornjiDeo(List<KomadOdece> komadi, PreporuceniKomadi preporuceniKomadi) {
        KieSession kieSession = kieContainer.newKieSession("gDPersRulesSession");
        for(KomadOdece komadOdece : komadi){
            kieSession.insert(komadOdece);
        }
        kieSession.setGlobal("hashMapColor", 2);
        kieSession.insert(preporuceniKomadi);
        kieSession.fireAllRules();


        kieSession.fireAllRules();

        return preporuceniKomadi;
    }

    public HashMap<DressCode, Integer> makeHashMapColor(){
        HashMap<DressCode, Integer> map = new HashMap<>();
        map.put(DressCode.LEZERAN, 250);
        map.put(DressCode.BLACKTIE, 300);
        return map;
    }
}
