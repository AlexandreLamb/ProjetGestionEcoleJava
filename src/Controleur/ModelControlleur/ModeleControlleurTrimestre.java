package Controleur.ModelControlleur;

import Modele.AnneScolaire;
import Modele.Trimestre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleControlleurTrimestre extends ModeleControlleur<Trimestre> {
    public ModeleControlleurTrimestre (){super();}

    @Override
    public Trimestre find(int id) {
        Trimestre trimestre  = null;
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM trimestre WHERE id = "+id);

            if (result.first()){
                int numero = result.getInt("numero");
                String dDebut = result.getString("debut");
                String dFin = result.getString("fin");
                AnneScolaire anneScolaire  = getAnneeScolaire(result.getInt("anneescolaire.id"));

                int idTri = result.getInt("id");
                trimestre = new Trimestre(idTri,numero,dDebut,dFin,anneScolaire);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return trimestre;
    }

    @Override
    public ArrayList<Trimestre> findAll() {
        ArrayList<Trimestre> trimestreArrayList = new ArrayList<>();

        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM trimestre WHERE 1");

            while (result.next()){
                int numero = result.getInt("numero");
                String dDebut = result.getString("debut");
                String dFin = result.getString("fin");
                AnneScolaire anneScolaire  = getAnneeScolaire(result.getInt("anneescolaire.id"));

                int id = result.getInt("id");
                trimestreArrayList.add(new Trimestre(id,numero,dDebut,dFin,anneScolaire));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return trimestreArrayList;
    }

    @Override
    public boolean update(Trimestre obj) {
        return false;
    }

    @Override
    public boolean delete(Trimestre obj) {
        return false;
    }

    @Override
    public void create(Trimestre obj) {
        try {
            String requete = "INSERT INTO trimestre (numero,debut,fin,`anneescolaire.id`) VALUE ('"+obj.getNumero()+"'," +
                    "'"+obj.getDebut()+"','"+obj.getFin()+"','"+obj.getAnneScolaire().getId()+"')";
            this.getConnexion().execute(requete);
        }catch (SQLException e){
                e.printStackTrace();
        }

    }
    public AnneScolaire getAnneeScolaire(int anneeScoId){
        AnneScolaire anneScolaire = null;
        try {ResultSet result = this.getConnexion().getConn().createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        ).executeQuery("SELECT * FROM anneescolaire WHERE id="+anneeScoId);
            if (result.first()){
                anneScolaire = new AnneScolaire(result.getInt("id"),result.getString("annee"));
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return anneScolaire;
    }
}
