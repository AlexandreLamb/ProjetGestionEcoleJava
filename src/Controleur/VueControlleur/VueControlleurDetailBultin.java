
package Controleur.VueControlleur;

import Controleur.ModelControlleur.ModeleControleurDetailBultin;
import Modele.Bulletin;
import Modele.DetailBulletin;

public class VueControlleurDetailBultin extends VueControlleur<DetailBulletin> {

    public VueControlleurDetailBultin(Bulletin bulletin){
        super(new ModeleControleurDetailBultin().findByBultin(bulletin.getId()));
    }
    @Override
    public String getColumnName(int column) {
        return DetailBulletin.class.getDeclaredFields()[column].getName();
    }

    @Override
    public int getColumnCount() {
        return DetailBulletin.class.getDeclaredFields().length-1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.getArrayList().get(rowIndex).getField(columnIndex);
    }

}
