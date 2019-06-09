package Vue;

import Controleur.ModelControlleur.*;
import Controleur.ReportingControlleur.Statistique;
import Controleur.VueControlleur.*;
import Modele.*;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

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
    private JFormattedTextField noteTextField;
    private JFormattedTextField appreciaitonTextField;
    private JButton ajouterButton1;
    private JComboBox professeurCombo;
    private JComboBox eleveCombo;
    private JFormattedTextField idEnseigant;
    private JFormattedTextField prenomEnseignant;
    private JFormattedTextField nomEnseignant;
    private JComboBox comboClasseEnseignant;
    private JButton CreateProf;
    private JButton updateProf;
    private JButton deleteProf;
    private JComboBox comboAnneescoCour;
    private JComboBox comboTriEnCours1;
    private JButton mettreAJourButton;
    private JFormattedTextField anneeText;
    private JButton ajouterButton2;
    private JComboBox comboTrimestre;
    private JFormattedTextField textDateDebut;
    private JFormattedTextField textDateFin;
    private JComboBox comboAnne;
    private JButton ajouterButtonTrimestre;
    private JComboBox combDiscipline;
    private JComboBox comboTriEnCours2;
    private JComboBox comboTriEnCours3;
    private JComboBox comboTriEval;
    private JTable bultinTable;
    private JTable evalTable;
    private JTable detailBultinTable;
    private JTextArea logtext;
    private JLabel moyenne;
    private JButton search;
    private JTable infosNoteTable;

    public Menu() {
        ModeleControleurClasse modeleControleurClasse = new ModeleControleurClasse();
        comboBox1.addItem("Tout les elves");

        modeleControleurClasse.findAll().forEach((classe)-> {
            comboBox1.addItem(classe);
            comboClasseEnseignant.addItem(classe);
            classeCombo.addItem(classe);
        });

        new ModelControlleurDiscipline().findAll().forEach((discipline -> {
            combDiscipline.addItem(discipline);
        }));


        formattedTextField3.setEditable(false);
        ModeleControlleurEleve modeleControlleurEleve = new ModeleControlleurEleve();
       modeleControlleurEleve.findElevesNoInscrit().forEach((eleve -> elevesCombo.addItem(eleve)));

       comboTrimestre.addItem(1);
       comboTrimestre.addItem(2);
       comboTrimestre.addItem(3);

       moyenne.setText("Moyenne : " +new ModeleControlleurEvaluation().moyenneAllEval());

        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                int id = (int) table2.getValueAt(table2.rowAtPoint(e.getPoint()),0);
                Eleve eleve = modeleControlleurEleve.find(id);
                formattedTextField1.setValue(eleve.getNom());
                formattedTextField2.setValue(eleve.getPrenom());
                formattedTextField3.setValue(eleve.getId());
                formattedTextField3.setEditable(false);
                bultinTable.setModel(new VueControlleurBultin(eleve));
                Statistique.createHistoNoteEleve(eleve);

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
                     moyenne.setText("Moyenne : "+new ModeleControlleurEvaluation().moyenneAllEval());
                }else {
                    Classe classe = (Classe) comboBox1.getSelectedItem();
                     vueControlleurEleve = new VueControlleurEleve(modeleControleurClasse.getClasseEleve(classe.getIdClasse()));
                     moyenne.setText("Moyenne : "+new ModeleControlleurEvaluation().moyenneAllEvalByClasse(classe));
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
                   modeleControleurClasse.findAll().forEach((classe)-> {
                       comboBox1.addItem(classe);
                       classeCombo.addItem(classe);
                       comboClasseEnseignant.addItem(classe);
                   });

               }

            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                        Eleve eleve = (Eleve) elevesCombo.getSelectedItem();
                        Classe classe = (Classe)  classeCombo.getSelectedItem();

                        modeleControlleurEleve.inscriptionEleve(eleve,classe.getIdClasse());
                        modeleControlleurEleve.findElevesNoInscrit().forEach((eleveNoInscrit -> elevesCombo.addItem(eleveNoInscrit)));

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
                modeleControlleurEleve.findElevesNoInscrit().forEach((eleve -> elevesCombo.addItem(eleve)));
               /* JFrame jFrame = new JFrame("Inscritpion");
                jFrame.setContentPane(new Inscritpion().contentPane);
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.pack();
                jFrame.setVisible(true);*/
               updateLog("");
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
                Eleve eleve = (Eleve) eleveCombo.getSelectedItem();
                Enseignant enseignant = (Enseignant) professeurCombo.getSelectedItem();
                Trimestre trimestre = (Trimestre) comboTriEval.getSelectedItem();
                double note =  Double.parseDouble(noteTextField.getText());
                String appreciaiton = appreciaitonTextField.getText();
                int bultinDetailId = new ModeleControleurDetailBultin().findDetailBultinId(enseignant,eleve,trimestre);
                Evaluation evaluation = new Evaluation(-1,bultinDetailId,note,appreciaiton);
                new ModeleControlleurEvaluation().create(evaluation);

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
               try {

                   Trimestre trimestre = (Trimestre) comboTriEnCours1.getSelectedItem();
                   trimestre.serialize("1");
                   trimestre = (Trimestre) comboTriEnCours2.getSelectedItem();
                   trimestre.serialize("2");
                   trimestre = (Trimestre) comboTriEnCours3.getSelectedItem();
                   trimestre.serialize("3");
                   AnneScolaire anneScolaire = (AnneScolaire) comboAnneescoCour.getSelectedItem();
                   anneScolaire.serialize();
               }catch (IOException serialize){
                   System.out.println("impossible serializer : " + serialize.getMessage());
               }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int id = (int) table1.getValueAt(table1.rowAtPoint(e.getPoint()),0);
                Enseignant enseignant = new ModeleControlleurEnseignant().find(id);
                nomEnseignant.setValue(enseignant.getNom());
                prenomEnseignant.setValue(enseignant.getPrenom());
                idEnseigant.setValue(enseignant.getId());

                combDiscipline.setSelectedItem(enseignant.getDisciplineEnseignant());
                comboClasseEnseignant.setSelectedItem(enseignant.getClasse());

            }
        });
        ajouterButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               new ModelControlleurAnnee().create(new AnneScolaire( anneeText.getText()));
            }
        });
        CreateProf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nomEnseignant.getText();
                String prenom = prenomEnseignant.getText();
                Discipline discipline =(Discipline) combDiscipline.getSelectedItem();
                Classe classe = (Classe) comboClasseEnseignant.getSelectedItem();

                new ModeleControlleurEnseignant().create(new Enseignant(-1,nom,prenom,discipline,classe));
                new ModeleControlleurEnseignant().findAll().forEach((enseignant -> {
                    professeurCombo.addItem(enseignant);
                }));
            }
        });
        bultinTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int id = (int) bultinTable.getValueAt(bultinTable.rowAtPoint(e.getPoint()),0);
                Bulletin bulletin = new ModelControlleurBultin().find(id);
                detailBultinTable.setModel(new VueControlleurDetailBultin(bulletin));
                Statistique.createHistoDetailBuletin(bulletin);

            }
        });
        detailBultinTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int id = (int) detailBultinTable.getValueAt(detailBultinTable.rowAtPoint(e.getPoint()),0);
                DetailBulletin detailBulletin = new ModeleControleurDetailBultin().find(id);
                evalTable.setModel(new VueControlleurEval(detailBulletin));

            }
        });
        deleteProf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModeleControlleurEnseignant().delete(new Enseignant(Integer.parseInt(idEnseigant.getText())));
                idEnseigant.setValue(null);
                nomEnseignant.setValue(null);
                prenomEnseignant.setValue(null);
                table1.setModel(new VueContrlleurEnseignant());

            }
        });
        updateProf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Classe classe = (Classe) comboClasseEnseignant.getSelectedItem();
                Discipline discipline = (Discipline) combDiscipline.getSelectedItem();
                String nom = nomEnseignant.getText();
                String prenom = prenomEnseignant.getText();
                int id =Integer.parseInt( idEnseigant.getText());
                new ModeleControlleurEnseignant().update(new Enseignant(id,nom,prenom,discipline,classe));
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = formattedTextField1.getText();
                table2.setModel(new VueControlleurEleve(new ModeleControlleurEleve().findBySearch(str)));
            }
        });
    }
    public void updateLog(String log){
        logtext.append("test\n");

    }


    private void createUIComponents() {
        logtext = new JTextArea();
        logtext.setEnabled(true);
        logtext.setEditable(false);
        table2 = new JTable(new VueControlleurEleve());
        table2.setAutoCreateRowSorter(true);

        table1 = new JTable(new VueContrlleurEnseignant());
        table1.setAutoCreateRowSorter(true);
        bultinTable = new JTable();
        bultinTable.setAutoCreateRowSorter(true);

        detailBultinTable = new JTable();
        detailBultinTable.setAutoCreateRowSorter(true);

        evalTable = new JTable();
        evalTable.setAutoCreateRowSorter(true);

        infosNoteTable = new JTable();

        anneClasse = new JComboBox();
        niveauClasse = new JComboBox();
        comboAnne = new JComboBox();
        comboAnneescoCour = new JComboBox();
        comboTriEnCours1 = new JComboBox();
        comboTriEnCours2 = new JComboBox();
        comboTriEnCours3 = new JComboBox();
        comboTriEval = new JComboBox();
        professeurCombo = new JComboBox();
        eleveCombo = new JComboBox();

        new ModeleControlleurTrimestre().findAll().forEach((trimestre -> {
            comboTriEnCours1.addItem(trimestre);
            comboTriEnCours2.addItem(trimestre);
            comboTriEnCours3.addItem(trimestre);
            comboTriEval.addItem(trimestre);
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

      try {
          AnneScolaire anneScolaire = AnneScolaire.deserialize();
          comboAnneescoCour.setSelectedItem(anneScolaire);
          Trimestre trimestre = Trimestre.deserialize("1");
          comboTriEnCours1.setSelectedItem(trimestre);
          trimestre = Trimestre.deserialize("2");
          comboTriEnCours2.setSelectedItem(trimestre);
          trimestre = Trimestre.deserialize("3");
          comboTriEnCours3.setSelectedItem(trimestre);
      } catch (IOException | ClassNotFoundException e){
          System.out.println("Pas d'année en cour ou de trimestre choisie");
      }

    }
}
