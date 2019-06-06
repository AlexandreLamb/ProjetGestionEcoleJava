package Controleur.VueControlleur;

import Controleur.ModelControlleur.ModelControlleurBultin;
import Controleur.ModelControlleur.ModeleControlleur;
import Modele.Bulletin;
import Modele.Eleve;

public class VueControlleurBultin extends VueControlleur<Bulletin> {
    public VueControlleurBultin(Eleve eleve){
        super(new ModelControlleurBultin().findAllBulletinByEleve(eleve) );

    }
    @Override
    public String getColumnName(int column) {
        return Bulletin.class.getDeclaredFields()[column].getName();
    }

    @Override
    public int getColumnCount() {
        return Bulletin.class.getDeclaredFields().length-2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         return this.getArrayList().get(rowIndex).getField(columnIndex);
    }

}
