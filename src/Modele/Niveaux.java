package Modele;

import java.util.ArrayList;

public class Niveaux {
    private ArrayList<String> nomNiveaux;
    private String niveau;
    private int id;

    public Niveaux(int id, String niveau){
        this.niveau = niveau;
        this.id = id;
    }
    public Niveaux(String niveau){
        this(-1,niveau);
    }

    public String getNiveau() {

        return niveau;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return niveau;
    }
}

