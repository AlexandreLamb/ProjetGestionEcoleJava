package Controleur.VueControlleur;

import javax.swing.*;
import java.util.Vector;

public class JcomboBoxVue extends JComboBox{
    public JcomboBoxVue(Vector<String> vector) {
        super(vector);
    }
    @Override
    public Object getSelectedItem() {
        return "yo";
    }

    @Override
    public void setPrototypeDisplayValue(Object prototypeDisplayValue) {
        super.setPrototypeDisplayValue(prototypeDisplayValue);
    }
}