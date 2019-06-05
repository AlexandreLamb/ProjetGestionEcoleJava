package Controleur.ModelControlleur;

import Modele.Bulletin;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModelControlleurBultin extends ModeleControlleur<Bulletin> {


    @Override
    public ArrayList<Bulletin> findAll() {
        return null;
    }

    @Override
    public Bulletin find(int id) {
        return null;
    }

    @Override
    public void create(Bulletin obj) {

    }
    public void create(int idIncription){
       /* try {
        //    String requete = "INSERT INTO bulletin (inscription.id,Trimestre.id) VALUES ('"+idIncription+","++")";
          //  this.getConnexion().execute(requete);
        }catch (SQLException e){
            System.out.println(e);
        }*/

    }

    @Override
    public boolean delete(Bulletin obj) {
        return false;
    }

    @Override
    public boolean update(Bulletin obj) {
        return false;
    }
}
