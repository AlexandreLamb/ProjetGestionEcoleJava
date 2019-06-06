package Modele;

public class Evaluation {
    private double note;
    private String appreciation;
    private int detailBultinId;
    private int id;

    public Evaluation(int id, int detailBultinId, double note, String appreciation){
        this.note = note;
        this.appreciation = appreciation;
        this.detailBultinId = detailBultinId;
        this.id = id;
    }

    public double getNote() {
        return note;
    }

    public int getId() {
        return id;
    }

    public int getDetailBultinId() {
        return detailBultinId;
    }

    public String getAppreciation() {
        return appreciation;
    }

    @Override
    public String toString() {
        return "bultin id  : " + detailBultinId  + " note : " + note + " appreciaiton : "  + appreciation;
    }
}
