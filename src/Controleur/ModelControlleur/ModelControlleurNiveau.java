package Controleur.ModelControlleur;

import Modele.Niveaux;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelControlleurNiveau extends ModeleControlleur <Niveaux> {
    public ModelControlleurNiveau(){
        super();
    }

    @Override
    public void create(Niveaux obj) {

    }

    @Override
    public Niveaux find(int id) {
        return null;
    }

    public Niveaux find(String name) {
        Niveaux niveau = null;
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM niveau WHERE nom = '"+name+"'");

            if (result.first()){
                niveau = new Niveaux(result.getInt("id"),result.getString("nom"));
            }

        }catch (SQLException e){

        }

        return niveau;
    }

    @Override
    public ArrayList<Niveaux> findAll() {
        ArrayList<Niveaux> niveauxArrayList = new ArrayList<>();

        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM niveau WHERE 1");

            while (result.next()){
                String niveau  = result.getString("nom");
                int id = result.getInt("id");
                niveauxArrayList.add(new Niveaux(id,niveau));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return niveauxArrayList ;
    }

    @Override
    public boolean delete(Niveaux obj) {
        return false;
    }

    @Override
    public boolean update(Niveaux obj) {
        return false;
    }
}

