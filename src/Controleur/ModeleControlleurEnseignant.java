package Controleur;

import Modele.AnneScolaire;
import Modele.Classe;
import Modele.Enseignant;
import Modele.Niveaux;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleControlleurEnseignant extends ModeleControlleur {
    public ModeleControlleurEnseignant(){
        super();
    }

    @Override
    public ArrayList<Enseignant> getAllLigneByTable(String table){
        ArrayList<Enseignant> enseignantArrayList = new ArrayList<>();
        String requete = "SELECT * FROM "+table+" WHERE 1";
        try {

            this.getConnexion().remplirChampsRequete(requete).forEach((enseignant)->{
               String[] params = enseignant.split(",");
                String discipline = getDisciplineEnseignant(Integer.parseInt(params[0]));
                enseignantArrayList.add(new Enseignant(Integer.parseInt(params[0]),params[1],params[2],discipline,getClasseEnseignant(Integer.parseInt(params[0]))));
            });

        }
        catch (SQLException e){
            System.out.println(e);
        }
        return enseignantArrayList;
    }
    public String getDisciplineEnseignant(int idEnseignant){
        String str = "";
        try {
            String requete = "SELECT  discipline.nom from discipline left join" +
                    " enseignant e on discipline.id = e.`Discipline.id` where e.id="+idEnseignant;
            str= this.getConnexion().remplirChampsRequete(requete).toString().replaceAll("]","");
            str= str.replace('[','\u0000');
        }catch (SQLException e){
            System.out.println(e);
        }
        return str;
    }
    public Classe getClasseEnseignant(int idEnseignant){
        Classe classe = null;
        try {
            String requete = "SELECT classe.id, classe.nom, niveau, anneeScolaire from classe left join " +
                    "enseignant e on classe.id = e.`Classe.id` where e.id ="+idEnseignant;
           String str = this.getConnexion().remplirChampsRequete(requete).toString();

           String [] params = str.substring(1,str.length()-1).split(",");
             classe = new Classe(Integer.parseInt(params[0]),params[1],new Niveaux(params[2]),new AnneScolaire(params[3]),new ModeleControleurClasse().getClasseEleve(Integer.parseInt(params[0])) );
        }catch (SQLException e){
            System.out.println(e);
        }
        return classe;
    }
}
