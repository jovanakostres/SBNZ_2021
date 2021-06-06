package com.ftn.PreporukaOdevneKombinacije.config;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CepKieSessionConfig {

    @Autowired
    private KieContainer kieContainer;

    @Bean(name = "cepIzvestajRulesSession")
    public KieSession izvestajKieSession() {
        return kieContainer.newKieSession("cepIzvestajRulesSession");
    }

    @Bean(name = "cepIzvestajKombRulesSession")
    public KieSession izvestajKombKieSession() {
        return kieContainer.newKieSession("cepIzvestajKombRulesSession");
    }

    @Bean(name = "cepOdbijen")
    public KieSession cepConfigKsessionRealtimeClock() {
        return kieContainer.newKieSession("cepOdbijenRulesSession");
    }


}
