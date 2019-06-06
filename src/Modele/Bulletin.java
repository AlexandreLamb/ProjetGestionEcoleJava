package Modele;

public class Bulletin {
    private int id;
    private String appreciation;
    private Trimestre trimestre;
    private Eleve eleve;
    private String moyenneGlobale;


    public Bulletin(int id, String appreciation,Eleve eleve, Trimestre trimestre){
        this.appreciation = appreciation;
        this.eleve = eleve;
        this.id = id;
        this.trimestre = trimestre;
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

    public Trimestre getTrimestre() {
        return trimestre;
    }
    public Object getField(int index){
        switch (index){
            case 0 : return getId();
            case 1 : return getAppreciation();
            case 2 : return getTrimestre();
            default: return "default";
        }
    }
}
