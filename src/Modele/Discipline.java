package Modele;

public class Discipline {
    private String discipline;

    public Discipline(String discipline){
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return this.discipline;
    }
}
