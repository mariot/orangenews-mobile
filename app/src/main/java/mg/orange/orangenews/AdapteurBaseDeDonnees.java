package mg.orange.orangenews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 2/11/2016.
 */
public class AdapteurBaseDeDonnees {
    static final String NOM_BASE_DE_DONNEES = "orangenews";
    static final String NOM_TABLE_DEPECHE = "depeche";
    static final String NOM_TABLE_ACTUALITE = "actualite";

    static final int VERSION_BASE_DE_DONNEES = 1;

    DatabaseHelper	DBHelper;
    Context context;
    SQLiteDatabase db;

    public AdapteurBaseDeDonnees(Context context){
        this.context = context;
        DBHelper = new DatabaseHelper(context);
    }

    public class DatabaseHelper extends SQLiteOpenHelper {

        Context	context;

        public DatabaseHelper(Context context) {
            super(context, NOM_BASE_DE_DONNEES, null, VERSION_BASE_DE_DONNEES);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + NOM_TABLE_DEPECHE + " (_id integer primary key autoincrement, "
                    + "heure text not null,"
                    + "titre text not null,"
                    + "contenu text"
                    + ");");
            db.execSQL("create table " + NOM_TABLE_ACTUALITE + " (_id integer primary key autoincrement, "
                    + "date text not null,"
                    + "titre text not null,"
                    + "image text,"
                    + "contenu text,"
                    + "categorie text not null,"
                    + "prefere text"
                    + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Toast.makeText(context, "Mise à jour de la Base de données version " + oldVersion + " vers " + newVersion, Toast.LENGTH_SHORT).show();
            db.execSQL("DROP TABLE IF EXISTS " + NOM_TABLE_DEPECHE);
            db.execSQL("DROP TABLE IF EXISTS " + NOM_TABLE_ACTUALITE);
            onCreate(db);
        }

    }

    public AdapteurBaseDeDonnees open(){
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    public void vider(){
        db.execSQL("DELETE FROM " + NOM_TABLE_DEPECHE);
        db.execSQL("DELETE FROM " + NOM_TABLE_ACTUALITE);
    }


    public long insererDepeche(String heure, String titre, String contenu) {
        ContentValues values = new ContentValues();
        values.put("heure", heure);
        values.put("titre", titre);
        values.put("contenu", contenu);
        return db.insert(NOM_TABLE_DEPECHE, null, values);
    }

    public boolean supprimerDepeche(long id) {
        return db.delete(NOM_TABLE_DEPECHE, "_id="+id, null)>0;
    }

    public Cursor listerDepeches(){
        return db.query(NOM_TABLE_DEPECHE, new String[]{
                "_id",
                "heure",
                "titre",
                "contenu"}, null, null, null, null, "heure DESC,titre DESC");
    }

    public Depeche getDepeche(String id) {
        Cursor c = db.query(NOM_TABLE_DEPECHE, new String[] {"_id", "heure", "titre", "contenu"},
                "_id" + " = " + id +"", null, null, null, null);
        return cursorToDepeche(c);
    }

    private Depeche cursorToDepeche(Cursor c) {
        if (c.getCount() == 0)
            return null;
        c.moveToFirst();
        Depeche boo = new Depeche(c.getString(1), c.getString(2), c.getString(3));
        c.close();
        return boo;
    }

    public ArrayList<Depeche> listerDepechesArray() {
        ArrayList<Depeche> resultat = new ArrayList<Depeche>();
        Cursor c = listerDepeches();
        if (c.moveToFirst()) {
            do {
                resultat.add(getDepeche(c.getString(0)));
            } while (c.moveToNext());
        }
        return resultat;
    }



    public long insererActualite(String date, String titre, String image, String contenu, String categorie, String prefere) {
        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("titre", titre);
        values.put("image", image);
        values.put("categorie", categorie);
        values.put("prefere", prefere);
        values.put("contenu", contenu);
        return db.insert(NOM_TABLE_ACTUALITE, null, values);
    }

    public boolean supprimerActualite(long id) {
        return db.delete(NOM_TABLE_ACTUALITE, "_id="+id, null)>0;
    }

    public Cursor listerActualites(){
        return db.query(NOM_TABLE_ACTUALITE, new String[]{
                "_id",
                "date",
                "titre",
                "image",
                "categorie",
                "prefere",
                "contenu"}, null, null, null, null, "date DESC,titre DESC");
    }

    public Cursor listerActualites(String categorie){
        return db.query(NOM_TABLE_ACTUALITE, new String[]{
                "_id",
                "date",
                "titre",
                "image",
                "categorie",
                "prefere",
                "contenu"}, "categorie" + " = '" + categorie +"'", null, null, null, "date DESC,titre DESC");
    }

    public Actualite getActualite(String id) {
        Cursor c = db.query(NOM_TABLE_ACTUALITE, new String[]{
                        "_id",
                        "date",
                        "titre",
                        "image",
                        "contenu",
                        "categorie",
                        "prefere"},
                "_id" + " = " + id +"", null, null, null, null);
        return cursorToActualite(c);
    }

    private Actualite cursorToActualite(Cursor c) {
        if (c.getCount() == 0)
            return null;
        c.moveToFirst();
        Actualite boo = new Actualite(c.getString(0),c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6));
        c.close();
        return boo;
    }

    public ArrayList<Actualite> listerActualitesArray() {
        ArrayList<Actualite> resultat = new ArrayList<Actualite>();
        Cursor c = listerActualites();
        if (c.moveToFirst()) {
            do {
                resultat.add(getActualite(c.getString(0)));
            } while (c.moveToNext());
        }
        return resultat;
    }

    public ArrayList<Actualite> listerActualitesArray(String categorie) {
        ArrayList<Actualite> resultat = new ArrayList<Actualite>();
        Cursor c = listerActualites(categorie);
        if (c.moveToFirst()) {
            do {
                resultat.add(getActualite(c.getString(0)));
            } while (c.moveToNext());
        }
        return resultat;
    }
}
