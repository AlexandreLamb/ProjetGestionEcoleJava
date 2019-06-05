package Modele;

public class Enseignant extends Personne {

    private Discipline disciplineEnseignant;
    private Classe classe;

    public Enseignant(int id, String nom, String prenom,Discipline discipline,Classe classe){
        super(id,nom,prenom);
        this.disciplineEnseignant = discipline;
        this.classe = classe;
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
    public int getId() {
        return super.getId();
    }

    @Override
    public String toString() {
        return "Nom : "  + getNom() + " | Discipline : " + this.disciplineEnseignant.toString() ;
    }

}

