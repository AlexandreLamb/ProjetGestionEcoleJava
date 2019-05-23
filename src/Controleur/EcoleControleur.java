package Controleur;

import Vue.EcoleVue;
import Vue.MiseAJourVue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcoleControleur extends JFrame implements ActionListener {
    private EcoleVue ecoleVue;

    public EcoleControleur(){
        this.ecoleVue = new EcoleVue();
        this.ecoleVue.getMiseAJour().addActionListener(this);
        this.ecoleVue.getRecherche().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.ecoleVue.getMiseAJour()){
             MiseAJourVue mise =new MiseAJourVue();
            this.ecoleVue.add(new JButton("test"));

        }
    }
}
