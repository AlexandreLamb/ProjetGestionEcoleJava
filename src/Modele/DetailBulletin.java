package Modele;

import java.util.ArrayList;

public class DetailBulletin {
    private int id;
    private String appreciaiton;
    private Enseignant enseignant;
    private int idBultin;
    private Double moyenneTrimestrielle;
    private ArrayList<Evaluation> evaluationArrayList;

    public DetailBulletin(int id, int idBultin, Enseignant enseignant, String appreciaiton, ArrayList<Evaluation> evaluationArrayList,Double moyenneTrimestrielle){
        this.id=id;
        this.idBultin=idBultin;
        this.appreciaiton =appreciaiton;
        this.enseignant = enseignant;
        this.evaluationArrayList= evaluationArrayList;
        this.moyenneTrimestrielle = moyenneTrimestrielle;
    }
    public DetailBulletin(int idBultin, Enseignant enseignant){
        this(-1,idBultin,enseignant,"",null,0.0);
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public String getAppreciaiton() {
        return appreciaiton;
    }

    public ArrayList<Evaluation> getEvaluationArrayList() {
        return evaluationArrayList;
    }

    public Double getMoyenneTrimestrielle() {
        return moyenneTrimestrielle;
    }

    public int getId() {
        return id;
    }

    public int getIdBultin() {
        return idBultin;
    }
    public Object getField(int index){
        switch (index){
            case 0 : return getId();
            case 1 : return getAppreciaiton();
            case 2 : return getEnseignant();
            case 3 : return getIdBultin();
            case 4 : return getMoyenneTrimestrielle();
            default: return "default";
        }
    }
}
