package Vue;

import Controleur.ModelControlleur.*;
import Controleur.VueControlleur.JcomboBoxVue;
import Controleur.VueControlleur.VueContrlleurEnseignant;
import Controleur.VueControlleur.VueControlleurEleve;
import Modele.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;

public class Menu {
    private JButton deleteButton;
    private JButton create;
    private JButton update;
    public JPanel Jpanel;
    private JTabbedPane tabbedPane1;
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JTable table2;
    private JComboBox comboBox1;
    private JLabel jLabel;
    private JFormattedTextField formattedTextField3;
    private JComboBox niveauClasse;
    private JTextField className;
    private JComboBox anneClasse;
    private JButton createClasse;
    private JComboBox classeCombo;
    private JComboBox elevesCombo;
    private JButton addButton;
    private JTable table1;
    private JButton ajouterButton;
    private JFormattedTextField noteTextField;
    private JFormattedTextField appreciaitonTextField;
    private JButton ajouterButton1;
    private JComboBox professeurCombo;
    private JComboBox eleveCombo;
    private JFormattedTextField formattedTextField6;
    private JFormattedTextField formattedTextField4;
    private JFormattedTextField formattedTextField5;
    private JFormattedTextField formattedTextField7;
    private JComboBox formattedTextField8;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JComboBox comboAnneescoCour;
    private JComboBox comboTriEnCours;
    private JButton mettreAJourButton;
    private JFormattedTextField anneeText;
    private JButton ajouterButton2;
    private JComboBox comboTrimestre;
    private JFormattedTextField textDateDebut;
    private JFormattedTextField textDateFin;
    private JComboBox comboAnne;
    private JButton ajouterButtonTrimestre;
    private JFormattedTextField formattedTextField9;

    public Menu() {
        ModeleControleurClasse modeleControleurClasse = new ModeleControleurClasse();
        comboBox1.addItem("Tout les elves");

        modeleControleurClasse.findName().forEach((name)-> comboBox1.addItem(name));
        modeleControleurClasse.findName().forEach((name)-> classeCombo.addItem(name));

        formattedTextField3.setEditable(false);
        ModeleControlleurEleve modeleControlleurEleve = new ModeleControlleurEleve();
       modeleControlleurEleve.findElevesNoInscrit().forEach((eleve -> elevesCombo.addItem("Id : "+eleve.getId()+" | Prenom : "+eleve.getPrenom())));

       comboTrimestre.addItem(1);
       comboTrimestre.addItem(2);
       comboTrimestre.addItem(3);

        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                int id = (int) table2.getValueAt(table2.rowAtPoint(e.getPoint()),0);
                Eleve eleve = modeleControlleurEleve.find(id);
                formattedTextField1.setValue(eleve.getNom());
                formattedTextField2.setValue(eleve.getPrenom());
                formattedTextField3.setValue(eleve.getId());
                formattedTextField3.setEditable(false);

            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeleControlleurEleve.update(new Eleve((int)formattedTextField3.getValue(),(String)formattedTextField1.getValue(),(String)formattedTextField2.getValue()));
                formattedTextField1.setValue(null);

                formattedTextField2.setValue(null);
                formattedTextField3.setValue(null);
                VueControlleurEleve vueControlleurEleve = new VueControlleurEleve();
                table2.setModel(vueControlleurEleve);
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VueControlleurEleve vueControlleurEleve = null;
                if(comboBox1.getSelectedItem().toString() == "Tout les elves"){
                     vueControlleurEleve = new VueControlleurEleve();
                }else {
                     vueControlleurEleve = new VueControlleurEleve(modeleControleurClasse.getClasseEleve(modeleControleurClasse.findId(comboBox1.getSelectedItem().toString())));
                }
                table2.setModel(vueControlleurEleve);
            }
        });
        createClasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(className.getText() != ""){
                   String nom = className.getText();
                   AnneScolaire anneScolaire = new AnneScolaire(anneClasse.getSelectedItem().toString());
                   Niveaux niveaux = new Niveaux(niveauClasse.getSelectedItem().toString());
                   new ModeleControleurClasse().create(new Classe(nom,niveaux,anneScolaire));
                   className.setText("");
                   modeleControleurClasse.findName().forEach((name)-> comboBox1.addItem(name));
                   modeleControleurClasse.findName().forEach((name)-> classeCombo.addItem(name));
               }

            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                modeleControlleurEleve.inscriptionEleve(modeleControlleurEleve.findElevesNoInscrit().get(elevesCombo.getSelectedIndex()),modeleControleurClasse.findId((String)classeCombo.getSelectedItem()));
            }
        });
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeleControlleurEleve.create(new Eleve(formattedTextField1.getText(),formattedTextField2.getText()));
                formattedTextField1.setValue(null);
                formattedTextField2.setValue(null);
                formattedTextField3.setValue(null);
                VueControlleurEleve vueControlleurEleve = new VueControlleurEleve();
                table2.setModel(vueControlleurEleve);
                modeleControlleurEleve.findElevesNoInscrit().forEach((eleve -> elevesCombo.addItem("Id : "+eleve.getId()+" | Prenom : "+eleve.getPrenom())));
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modeleControlleurEleve.delete(new Eleve((int)formattedTextField3.getValue(),(String)formattedTextField1.getValue(),(String)formattedTextField2.getValue()));
                formattedTextField1.setValue(null);

                formattedTextField2.setValue(null);
                formattedTextField3.setValue(null);
                VueControlleurEleve vueControlleurEleve = new VueControlleurEleve();
                table2.setModel(vueControlleurEleve);
            }
        });



        professeurCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int state = e.getStateChange();
                if (state == ItemEvent.SELECTED){

                    eleveCombo.removeAllItems();
                    Enseignant enseignant = (Enseignant) professeurCombo.getSelectedItem();
                    new ModeleControlleurEnseignant().getEleveByClasse(enseignant.getId()).forEach(eleve -> {
                        eleveCombo.addItem(eleve);
                    });
                }

            }
        });
        ajouterButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ajouterButtonTrimestre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numero = (int) comboTrimestre.getSelectedItem();
                String dDebut = textDateDebut.getText();
                String dFin = textDateFin.getText();
                AnneScolaire anneScolaire = (AnneScolaire) comboAnne.getSelectedItem();
                new ModeleControlleurTrimestre().create(new Trimestre(-1,numero,dDebut,dFin,anneScolaire));
            }
        });
        mettreAJourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Trimestre trimestre = (Trimestre) comboTriEnCours.getSelectedItem();
                AnneScolaire anneScolaire = (AnneScolaire) comboAnneescoCour.getSelectedItem();
                trimestre.serialize();
                anneScolaire.serialize();
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int id = (int) table1.getValueAt(table1.rowAtPoint(e.getPoint()),0);
                Enseignant enseignant = new ModeleControlleurEnseignant().find(id);

            }
        });
    }


    private void createUIComponents() {
        table2 = new JTable(new VueControlleurEleve());
        table2.setAutoCreateRowSorter(true);

        table1 = new JTable(new VueContrlleurEnseignant());
        table1.setAutoCreateRowSorter(true);

        anneClasse = new JComboBox();
        niveauClasse = new JComboBox();
        comboAnne = new JComboBox();
        comboAnneescoCour = new JComboBox();
        comboTriEnCours = new JComboBox();
        professeurCombo = new JComboBox();
        eleveCombo = new JComboBox();

        new ModeleControlleurTrimestre().findAll().forEach((trimestre -> {
            comboTriEnCours.addItem(trimestre);
        }));

        new ModelControlleurAnnee().findAll().forEach((anneScolaire -> {
            anneClasse.addItem(anneScolaire);
            comboAnne.addItem(anneScolaire);
            comboAnneescoCour.addItem(anneScolaire);
        }));
        new ModelControlleurNiveau().findAll().forEach((niveaux -> {
            niveauClasse.addItem(niveaux);
        }));
        new ModeleControlleurEnseignant().findAll().forEach((enseignant -> {
            professeurCombo.addItem(enseignant);
        }));
        Enseignant enseignant = (Enseignant) professeurCombo.getSelectedItem();
      new ModeleControlleurEnseignant().getEleveByClasse(enseignant.getId()).forEach(eleve -> {
            eleveCombo.addItem(eleve);
        });

      AnneScolaire anneScolaire = AnneScolaire.deserialize();
      comboAnneescoCour.setSelectedItem(anneScolaire);

      Trimestre trimestre = Trimestre.deserialize();
      comboTriEnCours.setSelectedItem(trimestre);
    }
}
