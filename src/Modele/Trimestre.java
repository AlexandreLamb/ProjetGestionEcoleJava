package Modele;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;

public class Trimestre implements Serializable {
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
        return "id : " +id + " debut : " + debut + " fin :"+fin  ;
    }
    public static Trimestre deserialize(String string) throws IOException, ClassNotFoundException {
        Trimestre trimestre = null;
            FileInputStream fileInputStream = new FileInputStream(string);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            trimestre = (Trimestre) objectInputStream.readObject();
            objectInputStream.close();


        return trimestre ;
    }
    public void serialize(String string) throws IOException  {
            FileOutputStream fileOutputStream = new FileOutputStream(string) ;
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
            objectOutputStream.close();


    }
}
