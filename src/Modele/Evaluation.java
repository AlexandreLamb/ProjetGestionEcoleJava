package Modele;

public class Evaluation {
    private int id;
    private int detailBultinId;
    private String appreciation;
    private double note;

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
    public Object getField(int index){
        switch (index){
            case 0 : return getId();
            case 1 : return getDetailBultinId();
            case 2 : return getAppreciation();
            case 3 : return getNote();
            default: return "default";
        }
    }
}
