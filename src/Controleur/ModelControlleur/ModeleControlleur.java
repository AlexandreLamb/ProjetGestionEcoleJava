package Controleur.ModelControlleur;

import Connexion.Connexion;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ModeleControlleur <T> {
    Connexion connexion;


    public ModeleControlleur(){
        try {
            this.connexion= new Connexion("base_projet_java","root","");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
    /**
     * Méthode de création
     * @param obj
     * @return boolean
     */
    public abstract void create(T obj);

    /**
     * Méthode pour effacer
     * @param obj
     * @return boolean
     */
    public abstract boolean delete(T obj);

    /**
     * Méthode de mise à jour
     * @param obj
     * @return boolean
     */
    public abstract boolean update(T obj);

    /**
     * Méthode de recherche des informations
     * @param id
     * @return T
     */
    public abstract T find(int id);

    public abstract ArrayList<T> findAll();

    public Connexion getConnexion() {
        return connexion;
    }
}
