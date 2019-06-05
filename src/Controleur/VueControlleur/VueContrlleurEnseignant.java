package Controleur.VueControlleur;

import Controleur.ModelControlleur.ModeleControlleurEnseignant;
import Modele.Enseignant;
import Modele.Personne;

public class VueContrlleurEnseignant extends VueControlleur<Enseignant> {
    public VueContrlleurEnseignant(){
        super(new ModeleControlleurEnseignant().findAll());
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
