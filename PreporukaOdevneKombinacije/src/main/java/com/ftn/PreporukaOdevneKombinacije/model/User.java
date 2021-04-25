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
    private Visina visina;
    private List<Integer> obimiCM;
    private BojaKoze bojaKoze;
}
