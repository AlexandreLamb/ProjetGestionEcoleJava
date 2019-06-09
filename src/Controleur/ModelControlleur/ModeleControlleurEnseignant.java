package Controleur.ModelControlleur;

import Modele.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleControlleurEnseignant extends ModeleControlleur<Enseignant>{
    public ModeleControlleurEnseignant(){
        super();
    }

    @Override
    public Enseignant find(int id) {
        Enseignant enseignant = null;

        try {
            ResultSet resultSet = this.getConnexion().getConn().createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(
                    "SELECT * FROM enseignant WHERE id = " + id);
            if (resultSet.first()){
                int idEnseignant = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                Classe classe = getClasseEnseignant(resultSet.getInt(("Classe.id")));
                Discipline discipline = getDisciplineEnseignant(resultSet.getInt("id"));
                enseignant = new Enseignant(idEnseignant,nom,prenom,discipline,classe);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return enseignant;
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
        try {
            String requete = "delete from enseignant where id="+ obj.getId();
            this.connexion.getConn().createStatement().executeUpdate(requete);
        }catch (SQLException e){
            System.out.println(e);
        }
        return true;
    }

    @Override
    public boolean update(Enseignant obj) {

        try {
            PreparedStatement ps = this.getConnexion().getConn().prepareStatement(
                    "UPDATE enseignant SET nom = ?, prenom = ?,`Classe.id` = ?,`Discipline.id`=? WHERE id = ?");

            // set the preparedstatement parameters
            ps.setString(1,obj.getNom());
            ps.setString(2,obj.getPrenom());
            ps.setInt(3,obj.getClasse().getIdClasse());
            ps.setInt(4,obj.getDisciplineEnseignant().getId());
            ps.setInt(5,obj.getId());

            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
            ps.close();
            PreparedStatement ps2 = this.getConnexion().getConn().prepareStatement(
                    "UPDATE enseignement SET classeId = ? WHERE enseignantId = ?");

            // set the preparedstatement parameters
            ps2.setInt(1,obj.getClasse().getIdClasse());
            ps2.setInt(2,obj.getId());
            // call executeUpdate to execute our sql update statement
            ps2.executeUpdate();
            ps2.close();


        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void create(Enseignant obj) {
        try {
            String requete = "INSERT INTO enseignant (nom, prenom,`Classe.id`,`Discipline.id`) " +
                    "VALUES ('"+obj.getNom()+"','"+ obj.getPrenom() +"','"+obj.getClasse().getIdClasse()+"','"+obj.getDisciplineEnseignant().getId()+"')";
            this.connexion.getConn().createStatement().executeUpdate(requete);


            addEnseignement(findLastInstertion());

        }catch (SQLException e){
            System.out.println(e);
        }
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

    public void addEnseignement(Enseignant enseignant){
        try {
            String requete = "INSERT INTO enseignement (enseignantId, classeId) VALUES ('"+enseignant.getId()+"','"+ enseignant.getClasse().getIdClasse()+"')";
            System.out.println(requete);
            this.getConnexion().execute(requete);

            ResultSet resultSet = this.getConnexion().getConn().createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(
                    "SELECT bulletin.id FROM bulletin\n" +
                            "INNER JOIN inscription\n" +
                            "on inscription.id = bulletin.`inscription.id`\n" +
                            "INNER JOIN classe \n" +
                            "on classe.id = inscription.classe\n" +
                            "INNER JOIN enseignement\n" +
                            "ON enseignement.classeId = classe.id\n" +
                            "WHERE enseignement.enseignantId ="+enseignant.getId());
            while (resultSet.next()){
                new ModeleControleurDetailBultin().create(new DetailBulletin(resultSet.getInt("bulletin.id"),enseignant));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Enseignant findLastInstertion(){
        Enseignant enseignant = null;
       try {
           ResultSet resultSet = this.getConnexion().getConn().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                   ResultSet.CONCUR_READ_ONLY).executeQuery(
                   "SELECT id,nom,prenom,`Classe.id` FROM enseignant order by id desc");
           if (resultSet.first()) {
               int idEnseignant = resultSet.getInt("id");
               String nom = resultSet.getString("nom");
               String prenom = resultSet.getString("prenom");
               Classe classe = getClasseEnseignant(resultSet.getInt(("Classe.id")));
               Discipline discipline = getDisciplineEnseignant(resultSet.getInt("id"));
               enseignant = new Enseignant(idEnseignant, nom, prenom, discipline, classe);
           }
       }
       catch (SQLException e ){
           e.printStackTrace();
       }
        return enseignant;
    }
    public Enseignant findByEnseignement(int idEnseignement){
        Enseignant enseignant = null;
        try {
            ResultSet resultSet = this.getConnexion().getConn().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(
                    "SELECT enseignant.* FROM `enseignant`\n" +
                            "INNER JOIN enseignement\n" +
                            "on enseignement.enseignantId = enseignant.id\n" +
                            "WHERE enseignement.id = "+idEnseignement);
            if (resultSet.first()) {
                int idEnseignant = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                Classe classe = getClasseEnseignant(resultSet.getInt(("Classe.id")));
                Discipline discipline = getDisciplineEnseignant(resultSet.getInt("id"));
                enseignant = new Enseignant(idEnseignant, nom, prenom, discipline, classe);
            }
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return enseignant;
    }

}
