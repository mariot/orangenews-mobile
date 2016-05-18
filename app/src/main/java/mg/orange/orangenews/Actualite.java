package mg.orange.orangenews;

/**
 * Created by User on 2/11/2016.
 */
public class Actualite {
    String id;
    String date;
    String titre;
    String image;
    String contenu;
    String categorie;

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    String prefere;

    public Actualite(String id, String date, String titre, String image, String contenu, String categorie, String prefere) {
        this.id = id;
        this.date = date;
        this.titre = titre;
        this.image = image;
        this.contenu = contenu;
        this.categorie = categorie;
        this.prefere = prefere;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefere() {

        return prefere;
    }

    public void setPrefere(String prefere) {
        this.prefere = prefere;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
