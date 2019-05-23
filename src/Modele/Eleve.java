package Modele;

public class Eleve extends Personne {
    public Eleve(int id, String nom, String prenome){
        super(id,nom,prenome);
    }
    public Eleve(String[] responses){

        super(Integer.parseInt(responses[0]),responses[1],responses[2]);
    }
}
