package Controleur.VueControlleur;

import Modele.DetailBulletin;
import Modele.Enseignant;
import Modele.Evaluation;

public class VueControlleurEval extends VueControlleur<Evaluation> {
    public VueControlleurEval(DetailBulletin detailBulletin){
        super(detailBulletin.getEvaluationArrayList());
    }
    @Override
    public String getColumnName(int column) {
        return Evaluation.class.getDeclaredFields()[column].getName();
    }

    @Override
    public int getColumnCount() {
        return Evaluation.class.getDeclaredFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.getArrayList().get(rowIndex).getField(columnIndex);
    }

}
