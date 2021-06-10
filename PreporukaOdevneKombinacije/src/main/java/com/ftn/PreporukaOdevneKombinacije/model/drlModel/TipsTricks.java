package com.ftn.PreporukaOdevneKombinacije.model.drlModel;

import java.util.ArrayList;
import java.util.List;

public class TipsTricks {
    private List<String> pros;
    private List<String> cons;

    public TipsTricks() {
        this.pros = new ArrayList<String>();
        this.cons = new ArrayList<String>();
    }

    public List<String> getPros() {
        return pros;
    }

    public void setPros(List<String> pros) {
        this.pros = pros;
    }

    public List<String> getCons() {
        return cons;
    }

    public void setCons(List<String> cons) {
        this.cons = cons;
    }

    @Override
    public String toString() {
        String s = "OK: \n";
        for(String tip : this.pros)
            s = s + tip + " ";
        s = s + "\nNOT OK:\n";
        for(String tip : this.cons)
            s = s + tip + " ";
        return s;
    }
}
