package Vue;

import javax.swing.*;

public class MiseAJourVue extends JFrame {
    JPanel mainPanel;
        public MiseAJourVue(){
           this.mainPanel = new JPanel();
           this.mainPanel.add(new JLabel("YOYOY"));
           this.mainPanel.add(new JLabel("YOYOY"));
           this.mainPanel.add(new JLabel("YOYOY"));
           this.mainPanel.add(new JLabel("YOYOY"));
        }

    public JLabel getMainPanel() {
        return new JLabel("tset");
    }
}
