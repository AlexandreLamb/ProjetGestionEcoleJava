package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EcoleVue extends JFrame {
    private JButton miseAJour ;
    private JButton recherche ;
   public EcoleVue(){
        Container container =  this.getContentPane();
        JPanel mainPanel = new JPanel();
        this.miseAJour = new JButton("Mise a jour");
        this.recherche = new JButton("Recherche d'informations");

        this.setTitle("Gestion Ecole");
        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(this.miseAJour);
        mainPanel.add(this.recherche);
        container.add(mainPanel);
        this.setVisible(true);
        this.setSize(new Dimension(500,500));
    }

    public JButton getMiseAJour() {
        return miseAJour;
    }

    public JButton getRecherche() {
        return recherche;
    }
}
