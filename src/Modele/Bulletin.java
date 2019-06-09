package Modele;

import java.util.ArrayList;

public class Bulletin {
    private int id;
    private String appreciation;
    private Trimestre trimestre;
    private Double moyenneGlobale;
    private Eleve eleve;
    private ArrayList<DetailBulletin> detailBulletins;



    public Bulletin(int id, String appreciation,Eleve eleve, Trimestre trimestre,Double moyenneGlobale,ArrayList<DetailBulletin>detailBulletins){
        this.appreciation = appreciation;
        this.eleve = eleve;
        this.id = id;
        this.trimestre = trimestre;
        this.moyenneGlobale = moyenneGlobale;
        this.moyenneGlobale  = moyenneGlobale;
        this.detailBulletins = detailBulletins;

    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public int getId() {
        return id;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public Double getMoyenneGlobale() {
        return moyenneGlobale;
    }

    public Trimestre getTrimestre() {
        return trimestre;
    }
    public Object getField(int index){
        switch (index){
            case 0 : return getId();
            case 1 : return getAppreciation();
            case 2 : return getTrimestre();
            case 3 : return getMoyenneGlobale();
            default: return "default";
        }
    }
    @Override
    public String toString() {
        return "id : "+id + " apprecaition" + appreciation;
    }
}
