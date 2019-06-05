package Modele;

import java.util.ArrayList;

public class Classe {
    private int idClasse;
    private String nomClasse;
    private Niveaux niveauClasse;
    private AnneScolaire anneScolaire;
    private ArrayList<Eleve> eleveArrayList;

    public Classe(int idClasse, String nomClasse, Niveaux niveauClasse,AnneScolaire anneScolaire,ArrayList<Eleve> eleveArrayList){
        this.idClasse = idClasse;
        this.nomClasse = nomClasse;
        this.niveauClasse = niveauClasse;
        this.anneScolaire = anneScolaire;
        this.eleveArrayList = eleveArrayList;
    }
    public Classe(int idClasse, String nomClasse, Niveaux niveauClasse,AnneScolaire anneScolaire){
        this( idClasse, nomClasse, niveauClasse, anneScolaire, new ArrayList<>());
    }

    public Classe(String nomClasse, Niveaux niveauClasse,AnneScolaire anneScolaire){
        this( -1, nomClasse, niveauClasse, anneScolaire, new ArrayList<>());
    }


    public void addEleve(Eleve eleve){
        this.eleveArrayList.add(eleve);
    }

    @Override
    public String toString() {
        return "Id : "+idClasse+ " nom Classe : " +nomClasse + " anneee"  +anneScolaire.getAnneScolaire()+ " nbr eleves : "+eleveArrayList.size();
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public String getAnneScolaire() {
        return anneScolaire.getAnneScolaire();
    }

    public String getNiveauClasse() {
        return niveauClasse.getNiveau();
    }
}

