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
    public Classe(String[] params){
        this(Integer.parseInt(params[0]),params[1],new Niveaux(params[2]), new AnneScolaire(params[3]));
    }

    public void addEleve(Eleve eleve){
        this.eleveArrayList.add(eleve);
    }

    @Override
    public String toString() {
        return "Id : "+idClasse+ " nom Classe : " +nomClasse + " anneee"  +anneScolaire.getAnneScolaire()+ " nbr eleves : "+eleveArrayList.size();
    }
}

