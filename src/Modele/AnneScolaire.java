package Modele;


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
    public static AnneScolaire deserialize() throws IOException, ClassNotFoundException {
        AnneScolaire anneScolaire = null;

           FileInputStream fileInputStream = new FileInputStream("anneEnCours");
           ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            anneScolaire = (AnneScolaire) objectInputStream.readObject();
           objectInputStream.close();
        return anneScolaire;

    }
    public void serialize() throws  IOException  {

            FileOutputStream fileOutputStream = new FileOutputStream("anneEnCours") ;
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
            objectOutputStream.close();
    }
}
