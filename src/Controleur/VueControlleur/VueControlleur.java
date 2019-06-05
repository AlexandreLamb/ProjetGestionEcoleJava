package Controleur.VueControlleur;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class VueControlleur <T> extends AbstractTableModel {

    private ArrayList<T> arrayList;

    public VueControlleur(ArrayList<T> arrayList){
        this.arrayList = arrayList;
    }



    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return "No Value";
    }

    @Override
    public int getRowCount() {
        return arrayList.size();
    }

    @Override
    public int getColumnCount() {
        return arrayList.get(0).getClass().getDeclaredFields().length;

    }

    @Override
    public String getColumnName(int column) {
        return "No Value" ;
    }

    public ArrayList<T> getArrayList() {
        return arrayList;
    }
}
