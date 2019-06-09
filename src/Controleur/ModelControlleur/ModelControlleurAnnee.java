package Controleur.ModelControlleur;

import Modele.AnneScolaire;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelControlleurAnnee extends ModeleControlleur<AnneScolaire> {
    @Override
    public void create(AnneScolaire obj) {
        try {
            String requete = "INSERT INTO anneescolaire (annee) VALUE ('"+obj.getAnneScolaire()+"')";
            this.getConnexion().execute(requete);
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    @Override
    public AnneScolaire find(int id) {
        return null;
    }
    public AnneScolaire find(String name){
        AnneScolaire anneScolaire = null;
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM anneescolaire WHERE annee = '"+name+"'");

            if (result.first()){
                anneScolaire = new AnneScolaire(result.getInt("id"),result.getString("annee"));
            }

        }catch (SQLException e){

        }

        return anneScolaire;
    }

    @Override
    public ArrayList<AnneScolaire> findAll() {
        ArrayList<AnneScolaire> anneScolaireArrayList = new ArrayList<>();

        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM anneescolaire WHERE 1");

            while (result.next()){
                String anneeScolaire  = result.getString("annee");
                int id = result.getInt("id");
                anneScolaireArrayList.add(new AnneScolaire(id,anneeScolaire));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return anneScolaireArrayList;
    }

    @Override
    public boolean delete(AnneScolaire obj) {
        return false;
    }

    @Override
    public boolean update(AnneScolaire obj) {
        return false;
    }
}
