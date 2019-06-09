package Controleur.ModelControlleur;

import Modele.Classe;
import Modele.Evaluation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleControlleurEvaluation extends ModeleControlleur<Evaluation>  {
    public ModeleControlleurEvaluation() { super();}

    @Override
    public boolean update(Evaluation obj) {
        return false;
    }

    @Override
    public void create(Evaluation obj) {
        try {
            String requete = "INSERT INTO evaluation (`note`,`appreciation`,`detailBulitn.id`) VALUES ('"+obj.getNote()+"','"+ obj.getAppreciation()+"','"+obj.getDetailBultinId()+"')";
            this.getConnexion().execute(requete);

        }catch (SQLException  e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Evaluation obj) {
        return false;
    }

    public double moyenneAllEval(){
        double notes = 0;
        int nbrNote = 0;
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM evaluation WHERE 1");
            while (result.next()){
                 notes +=  result.getInt("note");
                 nbrNote++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return notes/nbrNote;
    }
    public double moyenneAllEvalByClasse(Classe classe){
        double notes = 0;
        int nbrNote = 0;
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT note FROM evaluation\n" +
                    "INNER JOIN detailbulletin \n" +
                    "on detailbulletin.id = evaluation.`detailBulitn.id`\n" +
                    "INNER JOIN bulletin\n" +
                    "on bulletin.id = detailbulletin.`Bulletin.id`\n" +
                    "INNER JOIN inscription\n" +
                    "ON inscription.id = bulletin.`inscription.id`\n" +
                    "INNER JOIN classe\n" +
                    "ON classe.id = inscription.classe\n" +
                    "WHERE classe.id ="+classe.getIdClasse());
            while (result.next()){
                notes +=  result.getInt("note");
                nbrNote++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return notes/nbrNote;
    }
    @Override
    public ArrayList<Evaluation> findAll() {
        return null;
    }

    public ArrayList<Evaluation> findEvaluationByDetailBuletin(int idDetailBultin){
        ArrayList<Evaluation> evaluationArrayList = new ArrayList<>();
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM evaluation WHERE `detailBulitn.id`="+idDetailBultin);
            while (result.next()){
                int id = result.getInt("id");
                String appreciation = result.getString("appreciation");
                double note =  result.getInt("note");
                evaluationArrayList.add(new Evaluation(id,idDetailBultin,note,appreciation));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return evaluationArrayList;
    }

    @Override
    public Evaluation find(int id) {
        return null;
    }
}
