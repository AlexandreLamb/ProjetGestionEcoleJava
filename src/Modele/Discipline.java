package Modele;

public class Discipline {
    int id;
    private String discipline;

    public Discipline(int id, String discipline){
        this.id = id;
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return this.discipline;
    }
}
