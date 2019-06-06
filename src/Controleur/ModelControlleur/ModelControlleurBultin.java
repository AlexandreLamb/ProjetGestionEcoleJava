package Controleur.ModelControlleur;

import Modele.Bulletin;
import Modele.Trimestre;

import java.io.IOException;
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
        try {
           String requete = "INSERT INTO bulletin (`inscription.id`,`Trimestre.id`) VALUES ('"+idIncription+"','"+ Trimestre.deserialize("1").getId() +"')";
          this.getConnexion().execute(requete);
             requete = "INSERT INTO bulletin (`inscription.id`,`Trimestre.id`) VALUES ('"+idIncription+"','"+ Trimestre.deserialize("2").getId() +"')";
            this.getConnexion().execute(requete);
             requete = "INSERT INTO bulletin (`inscription.id`,`Trimestre.id`) VALUES ('"+idIncription+"','"+ Trimestre.deserialize("3").getId() +"')";
            this.getConnexion().execute(requete);

        }catch (SQLException | IOException | ClassNotFoundException e){
                e.printStackTrace();
        }

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
