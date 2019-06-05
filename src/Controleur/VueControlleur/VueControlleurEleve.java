package Controleur.VueControlleur;

import Controleur.ModelControlleur.ModeleControlleurEleve;
import Modele.Eleve;
import Modele.Personne;

import java.util.ArrayList;

public class VueControlleurEleve extends VueControlleur<Eleve> {

    public VueControlleurEleve(ArrayList<Eleve> arrayList){
       super(arrayList);
    }
    public VueControlleurEleve(){
        super(new ModeleControlleurEleve().findAll());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.getArrayList().get(rowIndex).getField(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return Personne.class.getDeclaredFields()[column].getName() ;
    }

    @Override
    public int getColumnCount() {
        return Personne.class.getDeclaredFields().length;
    }
}
