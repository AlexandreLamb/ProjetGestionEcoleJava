package Controleur;

import Modele.Eleve;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleControlleurEleve extends ModeleControlleur {

    public ModeleControlleurEleve(){
        super();
    }

    @Override
    public ArrayList<Eleve> getAllLigneByTable(String table){
        ArrayList<Eleve> eleveArrayList = new ArrayList<>();
        String requete = "SELECT * FROM "+table+" WHERE 1";
        try {

              this.getConnexion().remplirChampsRequete(requete).forEach((eleve)->{
                  eleveArrayList.add(new Eleve(eleve.toString().split(",")));
            });

        }
        catch (SQLException e){
            System.out.println(e);
        }
        return eleveArrayList;
    }
    public void addEleve(Eleve eleve){
        try {
            String requete = "INSERT INTO eleve (nom, prenom) VALUES ('"+eleve.getNom()+"','"+ eleve.getPrenom() +"')";
            this.getConnexion().execute(requete);
        }catch (SQLException e){
            System.out.println(e);
        }
    }
    public void inscriptionEleve(Eleve eleve, int idClasse){
        try {
            String requete = "INSERT INTO inscription (classe, eleve) VALUES ('"+idClasse+"','"+ eleve.getId()+"')";
            this.getConnexion().execute(requete);
        }catch (SQLException e){
            System.out.println(e);
        }
    }
}
