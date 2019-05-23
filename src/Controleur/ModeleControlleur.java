package Controleur;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleControlleur extends RequestControlleur {

    public ModeleControlleur(){
        super();
    }
    public ArrayList getAllLigneByTable(String table){
        ArrayList lignes = new ArrayList();
        String requete = "SELECT * FROM "+table+" WHERE 1";

        try {
            lignes =  this.getConnexion().remplirChampsRequete(requete);
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return lignes;
    }



}
