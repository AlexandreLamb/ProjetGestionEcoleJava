package Controleur.ModelControlleur;

import Modele.AnneScolaire;
import Modele.Classe;
import Modele.Eleve;
import Modele.Niveaux;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleControleurClasse extends ModeleControlleur<Classe> {
    public ModeleControleurClasse() {
        super();
    }

    @Override
    public ArrayList<Classe> findAll() {
        ArrayList<Classe> classeArrayList = new ArrayList<>();
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM classe WHERE 1");

            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                Niveaux niveaux = getClasseNiveau(result.getInt("niveau"));
                AnneScolaire anneScolaire = getClasseAnneScolaire(result.getInt("anneescolaire"));
                ArrayList<Eleve> eleveArrayList = getClasseEleve(result.getInt("id"));
                classeArrayList.add(new Classe(id, nom, niveaux, anneScolaire, eleveArrayList));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classeArrayList;
    }

    public ArrayList<String> findName() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT nom FROM classe WHERE 1");

            while (result.next()) {
                String nom = result.getString("nom");
                stringArrayList.add(nom);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stringArrayList;
    }

    public int findId(String className) {
        int id = 0;
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT id FROM classe WHERE nom ='" + className + "'");

            if (result.first()) {
                id = result.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Classe find(int id) {
        Classe classe = null;
        try {
            ResultSet result = this.getConnexion().getConn().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM classe WHERE id ='" + id + "'");

            if (result.first()) {
                int idClasse = result.getInt("id");
                String nom = result.getString("nom");
                Niveaux niveaux = getClasseNiveau(result.getInt("niveau"));
                AnneScolaire anneScolaire = getClasseAnneScolaire(result.getInt("anneescolaire"));
                ArrayList<Eleve> eleveArrayList = getClasseEleve(result.getInt("id"));
                classe = new Classe(idClasse, nom, niveaux, anneScolaire, eleveArrayList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classe;
    }

    @Override
    public void create(Classe obj) {

        Niveaux niveau = new ModelControlleurNiveau().find(obj.getNiveauClasse());
        AnneScolaire anneScolaire = new ModelControlleurAnnee().find(obj.getAnneScolaire());
        try {
            this.connexion.getConn().createStatement().executeUpdate("INSERT INTO classe(nom,niveau,anneeScolaire) VALUES ('" + obj.getNomClasse() + "','" +
                    niveau.getId() + "','" + anneScolaire.getId() + "')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Classe obj) {
        return false;
    }

    @Override
    public boolean delete(Classe obj) {
        return false;
    }

    public Niveaux getClasseNiveau(int id) throws SQLException {
        Niveaux niveaux = null;
        ResultSet result = this.getConnexion().getConn().createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        ).executeQuery("SELECT * FROM niveau WHERE id = " + id);
        if (result.first())
            niveaux = new Niveaux(result.getInt("id"), result.getString("nom"));

        return niveaux;
    }

    public AnneScolaire getClasseAnneScolaire(int id) throws SQLException {
        AnneScolaire anneScolaire = null;
        ResultSet result = this.getConnexion().getConn().createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        ).executeQuery("SELECT * FROM anneescolaire WHERE id = " + id);
        if (result.first())
            anneScolaire = new AnneScolaire(result.getInt("id"), result.getString("annee"));
        return anneScolaire;
    }

    public ArrayList<Eleve> getClasseEleve(int idClasse) {
        ArrayList<Eleve> eleveArrayList = new ArrayList<>();

        try {
            ResultSet resultSet = this.getConnexion().getConn().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(
                    "SELECT eleve.id, eleve.nom, eleve.prenom FROM eleve " +
                            "LEFT JOIN inscription i on eleve.id = i.eleve where i.classe =" + idClasse + " GROUP BY eleve");
            while (resultSet.next()) {
                eleveArrayList.add(new Eleve(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom")));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return eleveArrayList;
    }


    public void addElevesInClasse(ArrayList<Eleve> eleveArrayList, int idClasse) {
        ModeleControlleurEleve modeleControlleurEleve = new ModeleControlleurEleve();
        eleveArrayList.forEach((eleve -> {
            modeleControlleurEleve.inscriptionEleve(eleve, idClasse);
        }));
    }

}
