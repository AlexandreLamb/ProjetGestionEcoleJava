package Controleur.ModelControlleur;

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
