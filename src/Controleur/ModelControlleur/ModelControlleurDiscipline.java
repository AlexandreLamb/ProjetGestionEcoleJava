package Controleur.ModelControlleur;

import Modele.Discipline;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelControlleurDiscipline extends ModeleControlleur<Discipline> {
    @Override
    public void create(Discipline obj) {

    }

    @Override
    public boolean update(Discipline obj) {
        return false;
    }

    @Override
    public boolean delete(Discipline obj) {
        return false;
    }

    @Override
    public ArrayList<Discipline> findAll() {
        ArrayList<Discipline> disciplineArrayList = new ArrayList<>();

        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM discipline WHERE 1");

            while (result.next()){
                String nom  = result.getString("nom");
                int id = result.getInt("id");
                disciplineArrayList.add(new Discipline(id,nom));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return disciplineArrayList;
    }

    @Override
    public Discipline find(int id) {
        return null;
    }
}
