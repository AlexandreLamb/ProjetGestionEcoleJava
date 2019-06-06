package Modele;

import java.util.ArrayList;

public class DetailBulletin {
    private String appreciaiton;
    private String moyenneTrimestrielle;
    private Enseignant enseignant;
    private ArrayList<Evaluation> evaluationArrayList;
    private int id;
    private int idBultin;

    public DetailBulletin(int id, int idBultin, Enseignant enseignant, String appreciaiton, ArrayList<Evaluation> evaluationArrayList){
        this.id=id;
        this.idBultin=idBultin;
        this.appreciaiton =appreciaiton;
        this.enseignant = enseignant;
        this.evaluationArrayList= evaluationArrayList;
    }
    public DetailBulletin(int idBultin, Enseignant enseignant){
        this(-1,idBultin,enseignant,"",null);
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public int getId() {
        return id;
    }

    public int getIdBultin() {
        return idBultin;
    }
}
