package Modele;

import java.util.Objects;

public class Discipline {
    int id;
    private String discipline;

    public Discipline(int id, String discipline){
        this.id = id;
        this.discipline = discipline;
    }

    public String getDiscipline() {
        return discipline;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.discipline;
    }

    @Override
    public boolean equals(Object obj) {
        Discipline discipline =  (Discipline) obj;
        if (discipline.discipline.equals(this.discipline)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.discipline);
        return hash;
    }
}
