package Controleur.ModelControlleur;

import Modele.Bulletin;
import Modele.DetailBulletin;
import Modele.Eleve;
import Modele.Trimestre;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelControlleurBultin extends ModeleControlleur<Bulletin> {


    @Override
    public ArrayList<Bulletin> findAll() {
        return null;
    }

    public ArrayList<Bulletin> findAllBulletinByEleve(Eleve eleve) {
        ArrayList<Bulletin> bulletinArrayList = new ArrayList<>();
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT bulletin.id, bulletin.`inscription.id`,bulletin.appreciation,bulletin.`Trimestre.id` FROM `bulletin` \n" +
                    "INNER JOIN inscription\n" +
                    "on inscription.id = bulletin.`inscription.id`\n" +
                    "WHERE inscription.eleve ="+eleve.getId());
            while (result.next()){
                int id = result.getInt("id");
                Eleve eleve1 = new ModeleControlleurEleve().findEleveByInscription(result.getInt("inscription.id"));
                Trimestre trimestre = new ModeleControlleurTrimestre().find(result.getInt("Trimestre.id"));
                String appreciation = result.getString("appreciation");
                ArrayList<DetailBulletin> detailBulletins = new ModeleControleurDetailBultin().findByBultin(id);
                Double moyenne = calculateMoyenne(detailBulletins);
                bulletinArrayList.add(new Bulletin(id,appreciation,eleve1,trimestre,moyenne,detailBulletins));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bulletinArrayList;
    }

    @Override
    public Bulletin find(int id) {
        Bulletin bulletin =null;
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * from bulletin where id ="+id);
            if (result.first()){
                int idBultin = result.getInt("id");
                Eleve eleve1 = new ModeleControlleurEleve().findEleveByInscription(result.getInt("inscription.id"));
                Trimestre trimestre = new ModeleControlleurTrimestre().find(result.getInt("Trimestre.id"));
                String appreciation = result.getString("appreciation");
                ArrayList<DetailBulletin> detailBulletins = new ModeleControleurDetailBultin().findByBultin(id);
                Double moyenne = calculateMoyenne(detailBulletins);
                bulletin = new Bulletin(id,appreciation,eleve1,trimestre,moyenne,detailBulletins);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bulletin;
    }

    @Override
    public void create(Bulletin obj) {

    }
    public double calculateMoyenne(ArrayList<DetailBulletin> detailBulletins){
        double moyenne = 0;
        for (int i = 0 ; i < detailBulletins.size(); i++){
            moyenne += detailBulletins.get(i).getMoyenneTrimestrielle();
        }
        return moyenne/detailBulletins.size();
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
