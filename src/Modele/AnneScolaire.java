package Modele;


import Connexion.Connexion;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;


public class AnneScolaire implements Serializable {
    private String anneScolaire;
    private int id;
    public AnneScolaire(int id,String anneScolaire){
        this.anneScolaire = anneScolaire;
        this.id = id;
    }
    public AnneScolaire(String anneScolaire){
        this(-1,anneScolaire);
    }

    public String getAnneScolaire() {
        return anneScolaire;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return anneScolaire;
    }
    public static AnneScolaire deserialize() {
        AnneScolaire anneScolaire = null;
       try {
           FileInputStream fileInputStream = new FileInputStream("anneEnCours");
           ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            anneScolaire = (AnneScolaire) objectInputStream.readObject();
           objectInputStream.close();
       }catch (IOException | ClassNotFoundException e){
           e.printStackTrace();
       }
        return anneScolaire;

    }
    public void serialize()  {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("anneEnCours") ;
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
            objectOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
