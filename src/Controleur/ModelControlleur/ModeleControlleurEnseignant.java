package Controleur.ModelControlleur;

import Modele.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleControlleurEnseignant extends ModeleControlleur<Enseignant>{
    public ModeleControlleurEnseignant(){
        super();
    }

    @Override
    public Enseignant find(int id) {
        return null;
    }

    @Override
    public ArrayList<Enseignant> findAll() {
        ArrayList<Enseignant> enseignantArrayList = new ArrayList<>();

        try {
            ResultSet resultSet = this.getConnexion().getConn().createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(
                    "SELECT * FROM enseignant WHERE 1");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                Classe classe = getClasseEnseignant(resultSet.getInt(("Classe.id")));
                Discipline discipline = getDisciplineEnseignant(resultSet.getInt("id"));
                enseignantArrayList.add(new Enseignant(id,nom,prenom,discipline,classe));
            }

        }catch (SQLException e){
                e.printStackTrace();
        }
        return enseignantArrayList;
    }

    @Override
    public boolean delete(Enseignant obj) {
        return false;
    }

    @Override
    public boolean update(Enseignant obj) {
        return false;
    }

    @Override
    public void create(Enseignant obj) {

    }

    public Discipline getDisciplineEnseignant(int idEnseignant){
        Discipline discipline = null;
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT discipline.id,discipline.nom from discipline left join enseignant e on" +
                    " discipline.id = e.`Discipline.id` where e.id ="+idEnseignant);
            if (result.first()) {
                discipline = new Discipline(result.getInt("id"), result.getString("nom"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return discipline;
    }
    public Classe getClasseEnseignant(int idClasse){
        return  new ModeleControleurClasse().find(idClasse);
    }
    public ArrayList<Eleve> getEleveByClasse(int idEnseingant){
        return new ModeleControleurClasse().getClasseEleve(getIdClasse(idEnseingant));
    }

    public int getIdClasse(int idEnseingant){
        int idClasse = -1;
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT enseignant.`Classe.id` from enseignant where id ="+idEnseingant);
            if (result.first()) {
                idClasse = result.getInt("Classe.id");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return idClasse;
    }
}
