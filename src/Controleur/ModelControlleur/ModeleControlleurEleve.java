package Controleur.ModelControlleur;

import Modele.Eleve;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Alexandre Lambert
 */
public class ModeleControlleurEleve extends ModeleControlleur<Eleve> {

    /**
     *
     */
    public ModeleControlleurEleve(){
        super();
    }

    /**
     * @return
     */
    @Override
    public ArrayList<Eleve> findAll(){
        ArrayList<Eleve> eleveArrayList = new ArrayList<>();

        try {
            ResultSet resultSet = this.getConnexion().getConn().createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(
                            "SELECT * FROM eleve WHERE 1");
            while (resultSet.next()){
                eleveArrayList.add(new Eleve(resultSet.getInt("id"),resultSet.getString("nom"),resultSet.getString("prenom")));
            }

        }catch (SQLException e){
            System.out.println(e);
        }
        return eleveArrayList;
    }

    @Override
    public Eleve find(int id) {
        Eleve eleve = null;
        try {
            ResultSet resultSet = this.getConnexion().getConn().createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(
                    "SELECT * FROM eleve WHERE id = "+id);
            if (resultSet.first()){
                eleve = new Eleve(resultSet.getInt("id"),resultSet.getString("nom"),resultSet.getString("prenom"));
            }

        }catch (SQLException e){
            System.out.println(e);
        }
        return eleve;
    }

    @Override
    public boolean update(Eleve obj) {

        try {
            PreparedStatement ps = this.getConnexion().getConn().prepareStatement(
                    "UPDATE eleve SET nom = ?, prenom = ? WHERE id = ?");

            // set the preparedstatement parameters
            ps.setString(1,obj.getNom());
            ps.setString(2,obj.getPrenom());
            ps.setInt(3,obj.getId());

            // call executeUpdate to execute our sql update statement
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean delete(Eleve obj) {
        try {
            String requete = "delete from eleve where id="+ obj.getId();
            this.connexion.getConn().createStatement().executeUpdate(requete);
        }catch (SQLException e){
            System.out.println(e);
        }
        return true;
    }

    /**
     * @param eleve
     */
    @Override
    public void create(Eleve eleve){
        try {
            String requete = "INSERT INTO eleve (nom, prenom) VALUES ('"+eleve.getNom()+"','"+ eleve.getPrenom() +"')";
            this.connexion.getConn().createStatement().executeUpdate(requete);
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    /**
     * @param eleve
     * @param idClasse
     */
    public void inscriptionEleve(Eleve eleve, int idClasse){
        try {
            String requete = "INSERT INTO inscription (classe, eleve) VALUES ('"+idClasse+"','"+ eleve.getId()+"')";
            this.getConnexion().execute(requete);

            ResultSet resultSet = this.getConnexion().getConn().createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(
                    "SELECT id FROM inscription WHERE eleve = "+eleve.getId());
            if (resultSet.first()){
                new ModelControlleurBultin().create(resultSet.getInt("id"));
            }

        }catch (SQLException e){
            System.out.println(e);
        }
    }


    /**
     * @return
     */
    public ArrayList<Eleve> findElevesNoInscrit(){
        ArrayList<Eleve> eleveArrayList = new ArrayList<>();

        try {
            ResultSet resultSet = this.getConnexion().getConn().createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(
                    "SELECT eleve.id ,eleve.nom, eleve.prenom\n" +
                            "FROM eleve\n" +
                            "       LEFT JOIN inscription i on eleve.id = i.eleve\n" +
                            "WHERE i.id IS NULL");
            while (resultSet.next()){
                eleveArrayList.add(new Eleve(resultSet.getInt("id"),resultSet.getString("nom"),resultSet.getString("prenom")));
            }

        }catch (SQLException e){
            System.out.println(e);
        }
        return eleveArrayList;
    }

    /**
     * @param idInscription
     * @return
     */
    public Eleve findEleveByInscription(int idInscription){
        Eleve eleve = null;
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT eleve.* FROM eleve \n" +
                    "INNER JOIN inscription \n" +
                    "on inscription.eleve=eleve.id\n" +
                    "WHERE inscription.id ="+idInscription);
            if (result.first()){
                eleve = new Eleve(result.getInt("id"),result.getString("nom"),result.getString("prenom"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  eleve;
    }
}
