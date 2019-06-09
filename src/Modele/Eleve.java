package Modele;

public class Eleve extends Personne {
    public Eleve(int id, String nom, String prenom){
        super(id,nom,prenom);
    }
    public Eleve( String nom, String prenom){
        super(-1,nom,prenom);
    }



    public Object getField(int index){
        switch (index){
            case 0 : return getId();
            case 1 : return getNom();
            case 2 : return getPrenom();
            default: return "default";
        }
    }

    @Override
    public String toString() {
        return "id : "+ getId() + " | nom : "+getNom();
    }
}