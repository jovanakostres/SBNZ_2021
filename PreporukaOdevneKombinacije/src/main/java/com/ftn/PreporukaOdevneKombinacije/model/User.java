package com.ftn.PreporukaOdevneKombinacije.model;

import com.ftn.PreporukaOdevneKombinacije.model.enums.BojaKoze;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Pol;
import com.ftn.PreporukaOdevneKombinacije.model.enums.TipTela;
import com.ftn.PreporukaOdevneKombinacije.model.enums.Visina;

import java.util.List;

public class User {
    private String username;
    private String password;
    private Pol pol;
    private TipTela tipTela;
    private int visina;
    private int kilaza;
    private int ramena, kukovi, struk; //za tip tela
    private BojaKoze bojaKoze;
    private List<KomadOdece> komadi;
}
