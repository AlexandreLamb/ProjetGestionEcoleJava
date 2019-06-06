package Controleur;

import Controleur.ModelControlleur.ModeleControleurClasse;
import Modele.*;
import Vue.EcoleVue;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        EcoleVue ecoleVue  =new EcoleVue();
        //System.out.println(Personne.class.getDeclaredFields()[0].getName());
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);




       /*   ModeleControlleurEleve modeleControlleur = new ModeleControlleurEleve();
        ArrayList<Eleve> eleveArrayList = modeleControlleur.findAll();
        for (int i =0 ; i < eleveArrayList.size() ; i++ ){
            System.out.println("id : "+eleveArrayList.get(i).getId()+" | nom : " + eleveArrayList.get(i).getNom() + " | prenom : " +eleveArrayList.get(i).getPrenom() );
        }

/*
        ModeleControlleurEnseignant modeleControlleurEnseignant = new ModeleControlleurEnseignant();
        ArrayList<Enseignant> enseignantArrayList = modeleControlleurEnseignant.getAllLigneByTable("enseignant");
        for (int i =0 ; i < enseignantArrayList.size() ; i++ ){
            System.out.println(enseignantArrayList.get(i) );
        }
*/       ModeleControleurClasse modeleControleurClasse = new ModeleControleurClasse();

       // modeleControleurClasse.addElevesInClasse(eleveArrayList,2);

        ArrayList<Classe> classeArrayList = modeleControleurClasse.findAll();


        for (int i =0 ; i < classeArrayList.size() ; i++ ){
            //System.out.println(classeArrayList.get(i).toString() );
        }
      /*  ModeleControlleurEvaluation modeleControlleurEvaluation = new ModeleControlleurEvaluation();
        ArrayList<Evaluation> evaluationArrayList = modeleControlleurEvaluation.getAllLigneByTable("evaluation");
        for (int i =0 ; i < evaluationArrayList.size() ; i++ ){
            System.out.println(evaluationArrayList.get(i).toString() );
        }*/
       // new EcoleVue();


    }

}
