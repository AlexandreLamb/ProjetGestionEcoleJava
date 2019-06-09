package Controleur;

import Connexion.Connexion;

import java.sql.SQLException;

public class RequestControlleur {
    private Connexion connexion;

    public RequestControlleur(){
        try {
            this.connexion= new Connexion("base_projet_java","root","");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public Connexion getConnexion() {
        return connexion;
    }
}
