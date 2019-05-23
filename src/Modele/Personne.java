package Modele;

public class Personne {

    private int id;
    private String nom;
    private String prenom;

    public Personne( int id, String nom,String prenom){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }
}
