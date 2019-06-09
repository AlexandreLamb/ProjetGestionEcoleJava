package Controleur.ModelControlleur;

import Modele.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleControleurDetailBultin extends ModeleControlleur<DetailBulletin> {
    public ModeleControleurDetailBultin() {
        super();
    }

    @Override
    public boolean update(DetailBulletin obj) {
        try {
            PreparedStatement ps = this.getConnexion().getConn().prepareStatement(
                    "UPDATE detailbulletin SET appreciation = ? WHERE id = ?");

            // set the preparedstatement parameters
            ps.setString(1,obj.getAppreciaiton());
            ps.setInt(2,obj.getId());

            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void create(DetailBulletin obj) {
        try {
            int idEnseignement = -1;
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT id FROM enseignement WHERE enseignantId ='" + obj.getEnseignant().getId() + "'");

            if (result.first()) {
                idEnseignement = result.getInt("id");
            }

            String requete = "INSERT INTO detailbulletin (`Bulletin.id`,`Enseignement.id`) VALUES ('" + obj.getIdBultin() + "','" + idEnseignement + "')";
            this.getConnexion().execute(requete);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean delete(DetailBulletin obj) {
        return false;
    }

    @Override
    public DetailBulletin find(int id) {
        DetailBulletin detailBulletin = null;
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM detailbulletin WHERE `id`=" + id);
            if (result.first()) {

                int idDetailBultin = result.getInt("id");
                int idBulitn = result.getInt("Bulletin.id");
                Enseignant enseignant = new ModeleControlleurEnseignant().findByEnseignement(result.getInt("Enseignement.id"));
                String appreciation = result.getString("appreciation");
                ArrayList<Evaluation> evaluationArrayList = new ModeleControlleurEvaluation().findEvaluationByDetailBuletin(id);
                Double moyenneTrimestre = calculeMoyenne(evaluationArrayList);
                detailBulletin = new DetailBulletin(idDetailBultin, idBulitn, enseignant, appreciation, evaluationArrayList, moyenneTrimestre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detailBulletin;
    }

    public int findDetailBultinId(Enseignant enseignant, Eleve eleve, Trimestre trimestre) {
        int idBuletin = 0;
        int id = 0;
        try {
            System.out.println("trimestreId : " + trimestre.getId() + " | eleveId : " + eleve.getId());
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT Bulletin.id FROM bulletin\n" +
                    "INNER JOIN inscription\n" +
                    "on inscription.id = Bulletin.`inscription.id`\n" +
                    "WHERE inscription.eleve =" + eleve.getId() + "\n" +
                    "AND bulletin.`Trimestre.id`=" + trimestre.getId());

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
                        "AND  detailbulletin.`Bulletin.id`=" + idBuletin);

                if (result.first()) {
                    id = result.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public double calculeMoyenne(ArrayList<Evaluation> evaluations) {
        double moyenne = 0;
        for (int i = 0; i < evaluations.size(); i++) {
            moyenne += evaluations.get(i).getNote();
        }
        return moyenne / evaluations.size();
    }

    @Override
    public ArrayList<DetailBulletin> findAll() {
        return null;
    }

    public ArrayList<DetailBulletin> findByBultin(int idBulitn) {
        ArrayList<DetailBulletin> detailBulletinArrayList = new ArrayList<>();
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM detailbulletin WHERE `Bulletin.id`=" + idBulitn);
            while (result.next()) {

                int id = result.getInt("id");
                Enseignant enseignant = new ModeleControlleurEnseignant().findByEnseignement(result.getInt("Enseignement.id"));
                String appreciation = result.getString("appreciation");
                ArrayList<Evaluation> evaluationArrayList = new ModeleControlleurEvaluation().findEvaluationByDetailBuletin(id);
                Double moyenne = calculeMoyenne(evaluationArrayList);
                detailBulletinArrayList.add(new DetailBulletin(id, idBulitn, enseignant, appreciation, evaluationArrayList, moyenne));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detailBulletinArrayList;
    }
}
