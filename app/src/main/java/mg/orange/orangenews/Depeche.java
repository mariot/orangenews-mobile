package mg.orange.orangenews;

/**
 * Created by User on 2/11/2016.
 */
public class Depeche {
    public Depeche(String heure, String titre, String contenu) {
        this.heure = heure;
        this.titre = titre;
        this.contenu = contenu;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    String heure;
    String titre;
    String contenu;
}
