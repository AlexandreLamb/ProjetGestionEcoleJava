package Controleur;

import Connexion.Connexion;
import Modele.Classe;
import Modele.Eleve;
import Modele.Enseignant;
import Vue.EcoleVue;

import java.sql.SQLException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

          ModeleControlleurEleve modeleControlleurEleve = new ModeleControlleurEleve();
        ArrayList<Eleve> eleveArrayList = modeleControlleurEleve.getAllLigneByTable("eleve");
        /*for (int i =0 ; i < eleveArrayList.size() ; i++ ){
            System.out.println("id : "+eleveArrayList.get(i).getId()+" | nom : " + eleveArrayList.get(i).getNom() + " | prenom : " +eleveArrayList.get(i).getPrenom() );
        }*/


        ModeleControlleurEnseignant modeleControlleurEnseignant = new ModeleControlleurEnseignant();
        ArrayList<Enseignant> enseignantArrayList = modeleControlleurEnseignant.getAllLigneByTable("enseignant");
        for (int i =0 ; i < enseignantArrayList.size() ; i++ ){
            System.out.println(enseignantArrayList.get(i) );
        }
/*        ModeleControleurClasse modeleControleurClasse = new ModeleControleurClasse();

        modeleControleurClasse.addElevesInClasse(eleveArrayList,2);

        ArrayList<Classe> classeArrayList = modeleControleurClasse.getAllLigneByTable("classe");

        for (int i =0 ; i < classeArrayList.size() ; i++ ){
            System.out.println(classeArrayList.get(i).toString() );
        }*/

    }

}
