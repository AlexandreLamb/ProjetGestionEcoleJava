package Controleur;

import Modele.AnneScolaire;
import Modele.Classe;
import Modele.Eleve;
import Modele.Niveaux;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleControleurClasse extends ModeleControlleur {
    public ModeleControleurClasse(){
        super();
    }

    @Override
    public ArrayList<Classe> getAllLigneByTable(String table){
        ArrayList<Classe> classArrayList = new ArrayList<>();
        String requete = "SELECT * FROM "+table+" WHERE 1";
        try {
             this.getConnexion().remplirChampsRequete(requete).forEach((classe)->{
                String [] params =classe.split(",");
                Niveaux niveaux = getClasseNiveau(Integer.parseInt(params[2]));
                AnneScolaire anneScolaire = getClasseAnneScolaire(Integer.parseInt(params[3].substring(0,1)));

                classArrayList.add(new Classe(Integer.parseInt(params[0]),params[1],niveaux,anneScolaire,getClasseEleve(Integer.parseInt(params[0]))));
            });

        }
        catch (SQLException e){
            System.out.println(e);
        }
        return classArrayList;
    }
    public Niveaux getClasseNiveau(int id){
        Niveaux niveaux = null;
        try {
            niveaux = new Niveaux(this.getConnexion().remplirChampsRequete("SELECT nom FROM niveau WHERE id = "+id).get(0));
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return niveaux;
    }
    public AnneScolaire getClasseAnneScolaire(int id){
        AnneScolaire anneScolaire = null;
        try {
            anneScolaire = new AnneScolaire(this.getConnexion().remplirChampsRequete("SELECT annee FROM anneescolaire WHERE id = "+id).get(0));
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return anneScolaire;
    }
    public ArrayList<Eleve> getClasseEleve(int idClasse){
        ArrayList<Eleve> eleveArrayList = new ArrayList<>();
        try {
            this.getConnexion().remplirChampsRequete("SELECT * FROM eleve LEFT JOIN inscription " +
                    "ON inscription.eleve = eleve.id WHERE inscription.classe ="+idClasse).forEach((eleve)->{


                eleveArrayList.add(new Eleve(eleve.split(",")));
            });
        }catch (SQLException e){
            System.out.println(e);
        }
        return eleveArrayList;
    }

    public void addElevesInClasse(ArrayList<Eleve> eleveArrayList, int idClasse){
        ModeleControlleurEleve modeleControlleurEleve = new ModeleControlleurEleve();
        eleveArrayList.forEach((eleve -> {
            modeleControlleurEleve.inscriptionEleve(eleve,idClasse);
        }));
    }
}
