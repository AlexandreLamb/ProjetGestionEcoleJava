package Controleur.ModelControlleur;

import Modele.Evaluation;

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

    @Override
    public Evaluation find(int id) {
        return null;
    }
}
