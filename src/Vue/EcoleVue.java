package Vue;

import javax.swing.*;

public class EcoleVue extends JFrame {
   public EcoleVue(){
       JFrame jFrame = new JFrame("Menu");
       jFrame.setContentPane(new Menu().Jpanel);
       jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       jFrame.pack();
       jFrame.setVisible(true);
   }
}
