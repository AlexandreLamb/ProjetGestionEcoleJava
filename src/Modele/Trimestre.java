package Modele;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;

public class Trimestre {
    private AnneScolaire anneScolaire;
    private String debut;
    private String fin;
    private int numero;
    private int id;

    public Trimestre(int id, int numero, String debut, String fin, AnneScolaire anneScolaire){
        this.id = id;
        this.numero = numero;
        this.debut = debut;
        this.fin = fin;
        this.anneScolaire = anneScolaire;
    }

    public int getId() {
        return id;
    }

    public String getDebut() {
        return debut;
    }

    public String getFin() {
        return fin;
    }

    public int getNumero() {
        return numero;
    }

    public AnneScolaire getAnneScolaire() {
        return anneScolaire;
    }

    @Override
    public String toString() {
        return "Numero : " +numero + " debut : " + debut + " fin :"+fin + " annee Scolaire : " + anneScolaire.getAnneScolaire() ;
    }
    public static Trimestre deserialize() {
        Trimestre trimestre = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("trimestreEnCours");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            trimestre = (Trimestre) objectInputStream.readObject();
            objectInputStream.close();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return trimestre ;
    }
    public void serialize()  {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("trimestreEnCours") ;
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
