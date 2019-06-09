package Controleur.ReportingControlleur;

import Controleur.ModelControlleur.ModelControlleurBultin;
import Controleur.ModelControlleur.ModeleControleurClasse;
import Controleur.ModelControlleur.ModeleControleurDetailBultin;
import Modele.Bulletin;
import Modele.DetailBulletin;
import Modele.Eleve;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.util.ArrayList;

public class Statistique {

    public Statistique(){

    }
    public static void createPieChart(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("cat 1", 10);
        dataset.setValue("cat 2", 20);
        dataset.setValue("cat 3", 30);

        JFreeChart chart = ChartFactory.createPieChart(
                "test",
                dataset,
                true,
                true,
                false
        );
        ChartFrame frame = new ChartFrame("test",chart);
        frame.pack();
        frame.setVisible(true);
    }

    public static void createPieChartNumberEleveByClasse(){
        DefaultPieDataset dataset = new DefaultPieDataset();
        new ModeleControleurClasse().findAll().forEach((classe -> {
            dataset.setValue(classe.getNomClasse(), classe.getEleveArrayList().size());
        }));
        JFreeChart chart = ChartFactory.createPieChart(
                "test",
                dataset,
                true,
                true,
                false
        );
        ChartFrame frame = new ChartFrame("test",chart);
        frame.pack();
        frame.setVisible(true);
    }
    public static void createHistoMoyenneClasse(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        new ModeleControleurClasse().findAll().forEach(classe -> {
           // dataset.setValue(classe.);
        });
    }
    public static void createHistoNoteEleve(Eleve eleve){
        ArrayList<Bulletin> bulletinArrayList = new ModelControlleurBultin().findAllBulletinByEleve(eleve);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        bulletinArrayList.forEach((bulletin -> {
            dataset.addValue(bulletin.getMoyenneGlobale(),""+bulletin.getId(),""+ bulletin.getTrimestre().getNumero());

        }));

        JFreeChart chart = ChartFactory.createBarChart(
                "Moyenne par Bulletin", // chart title
                "Trimestre", // domain axis label
                "Note", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                false, // include legend
                true, // tooltips?
                false // URLs?
        );
        ChartFrame frame = new ChartFrame("test",chart);
        frame.pack();
        frame.setVisible(true);

    }
    public static void createHistoDetailBuletin(Bulletin bulletin){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<DetailBulletin> detailBulletins = new ModeleControleurDetailBultin().findByBultin(bulletin.getId());
        detailBulletins.forEach((detailBulletin -> {
            dataset.addValue(detailBulletin.getMoyenneTrimestrielle(),""+detailBulletin.getId(),detailBulletin.getEnseignant().getDisciplineEnseignant().getDiscipline());
        }));
        JFreeChart chart = ChartFactory.createBarChart(
                "Moyenne par Matiere", // chart title
                "Matier", // domain axis label
                "Note", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                false, // include legend
                true, // tooltips?
                false // URLs?
        );
        ChartFrame frame = new ChartFrame("test",chart);
        frame.pack();
        frame.setVisible(true);
    }
}
