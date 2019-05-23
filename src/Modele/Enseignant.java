package Modele;

public class Enseignant extends Personne {

    private Discipline disciplineEnseignant;
    private Classe classe;

    public Enseignant(int id, String nom, String prenom,String discipline,Classe classe){
        super(id,nom,prenom);
        this.disciplineEnseignant = new Discipline(discipline);
        this.classe = classe;
    }

    @Override
    public String toString() {
        return "Nom : "  + getNom() + " discipline : " + this.disciplineEnseignant.toString() + " class : " + classe.toString() ;
    }

}

