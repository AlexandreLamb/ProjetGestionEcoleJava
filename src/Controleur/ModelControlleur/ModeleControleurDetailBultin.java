package Controleur.ModelControlleur;

import Modele.*;

import javax.swing.table.TableRowSorter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleControleurDetailBultin extends ModeleControlleur<DetailBulletin>  {
    public ModeleControleurDetailBultin(){super();}

    @Override
    public boolean update(DetailBulletin obj) {
        return false;
    }

    @Override
    public void create(DetailBulletin obj) {
        try {
            int idEnseignement = -1;
                ResultSet result = this.getConnexion().getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                ).executeQuery("SELECT id FROM enseignement WHERE enseignantId ='"+obj.getEnseignant().getId()+"'");

                if (result.first()){
                     idEnseignement = result.getInt("id");
                }

            String requete = "INSERT INTO detailbulletin (`Bulletin.id`,`Enseignement.id`) VALUES ('"+obj.getIdBultin()+"','"+idEnseignement  +"')";
            this.getConnexion().execute(requete);

        }catch (SQLException  e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean delete(DetailBulletin obj) {
        return false;
    }

    @Override
    public DetailBulletin find(int id) {
        return null;
    }
    public int findDetailBultinId(Enseignant enseignant, Eleve eleve, Trimestre trimestre){
        int idBuletin =0;
        int id = 0;
        try {
            System.out.println("trimestreId : " +trimestre.getId()+" | eleveId : "+ eleve.getId());
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT Bulletin.id FROM bulletin\n" +
                    "INNER JOIN inscription\n" +
                    "on inscription.id = Bulletin.`inscription.id`\n" +
                    "WHERE inscription.eleve ="+eleve.getId() +"\n"+
                    "AND bulletin.`Trimestre.id`="+trimestre.getId());

            if (result.first()) {
                idBuletin = result.getInt("id");
                System.out.println(" | butlinId : " + idBuletin);


                result = this.getConnexion().getConn().createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                ).executeQuery("SELECT detailbulletin.id FROM `detailbulletin` \n" +
                        "INNER JOIN enseignement\n" +
                        "ON enseignement.id = detailbulletin.`Enseignement.id`\n" +
                        "INNER JOIN enseignant \n" +
                        "on enseignant.id = enseignement.enseignantId\n" +
                        "WHERE enseignant.id =" + enseignant.getId() + "\n" +
                        "AND  detailbulletin.`Bulletin.id`="+idBuletin);

                if (result.first()) {
                    id = result.getInt("id");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  id;
    }

    @Override
    public ArrayList<DetailBulletin> findAll() {
        return null;
    }
}
