package mg.orange.orangenews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AdapteurBaseDeDonnees bdd;

    ArrayList<Depeche> arrayListDepeches;
    AdapteurDepeche adapteurDepeche;
    ListView listViewDepeches;

    Handler handler;
    MainActivity foo = this;

    private List<DepecheFeed> depecheFeeds;
    private List<ActualiteFeed> actualiteFeeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listViewDepeches = (ListView) findViewById(R.id.depechesListView);
        arrayListDepeches = new ArrayList<>();

        bdd = new AdapteurBaseDeDonnees(this);
        bdd.open();

        handler = new Handler();

        new Thread() {
            public void run() {
/*
                bdd.vider();

                bdd.insererActualite("01 Mars", "Volley ball : Les gendarmes du GNVB s'imposent face aux algériens du Fianar ","http://www.orange.m/logo.jpeg","L’équipe malgache de la « Gendarmerie nationale volley-ball » (GNVB) s’est imposée ce mardi 29 mars en Egypte face aux algériens du Fanar (3-2). Match comptant pour la phase de classement (9ème - 16ème place) de la Coupe d’Afrique des Clubs Champions 2016. Avec cette victoire, les gendarmes ont l’occasion de se battre pour la 9ème place du tournoi lors de son prochain match. Madagascar peut enregistrer un bon classement, ce serait une première depuis maintenant de nombreuses années.\n" +
                        "\n" +
                        "En terminant 3ème de son groupe lors des phases de poule (3 victoires et 2 défaites), le GNVB ne s’était pas qualifié pour les quarts de finale.","P","0");
                bdd.insererActualite("02 Mars", "Le film de Majunga fait son tour du monde 2016 ","http://www.orange.m/logo.jpeg","L’équipe malgache de la « Gendarmerie nationale volley-ball » (GNVB) s’est imposée ce mardi 29 mars en Egypte face aux algériens du Fanar (3-2). Match comptant pour la phase de classement (9ème - 16ème place) de la Coupe d’Afrique des Clubs Champions 2016. Avec cette victoire, les gendarmes ont l’occasion de se battre pour la 9ème place du tournoi lors de son prochain match. Madagascar peut enregistrer un bon classement, ce serait une première depuis maintenant de nombreuses années.\n" +
                        "\n" +
                        "En terminant 3ème de son groupe lors des phases de poule (3 victoires et 2 défaites), le GNVB ne s’était pas qualifié pour les quarts de finale.","P","0");
                bdd.insererActualite("02 Mars", "Les gagnants du premier mini-marathon photo “Alao Sary” sont connus ","http://www.orange.m/logo.jpeg","L’équipe malgache de la « Gendarmerie nationale volley-ball » (GNVB) s’est imposée ce mardi 29 mars en Egypte face aux algériens du Fanar (3-2). Match comptant pour la phase de classement (9ème - 16ème place) de la Coupe d’Afrique des Clubs Champions 2016. Avec cette victoire, les gendarmes ont l’occasion de se battre pour la 9ème place du tournoi lors de son prochain match. Madagascar peut enregistrer un bon classement, ce serait une première depuis maintenant de nombreuses années.\n" +
                        "\n" +
                        "En terminant 3ème de son groupe lors des phases de poule (3 victoires et 2 défaites), le GNVB ne s’était pas qualifié pour les quarts de finale.","P","0");

                bdd.insererDepeche("07:00", "Autour de nous", "Autour de nous, tout devient Smart. Maintenant, nous avons la possibilité de rendre nos périphériques « connectés ».");
                bdd.insererDepeche("08:10","C’est une révolution","C’est une révolution qui se passe sous nos yeux en ce moment même, et ça nous affecte tous. Beaucoup l’appelle « Internet of Things » ou IoT pour les intimes ");
                bdd.insererDepeche("08:30","Mozilla a déjà","Mozilla a déjà commencé à participer à ce grand changement en utilisant la technologie de Firefox OS comme base");
                bdd.insererDepeche("08:45","Mozilla va explorer","Mozilla va explorer et prototyper de nouveaux cas d’utilisation des « Connected Devices » en gardant comme but l’expérience et la satisfaction des utilisateurs");
                bdd.insererDepeche("09:00","En se concentrant","En se concentrant sur des produits et technologies qui vont permettre aux utilisateurs");
                bdd.insererDepeche("09:20","d’accéder à l","d’accéder à leur monde de « Connected Devices », nous nous assurons que ces utilisateurs soient indépendants et en sécurité.");
                bdd.insererDepeche("09:45","Pourquoi est-ce","Pourquoi est-ce important ? Parce que cette année, le nombre va dépasser le nombre d’utilisateurs ");
                bdd.insererDepeche("10:00","Les experts estim","Les experts estiment que l'IoT sera composé de près de 50 millions d'objets d'ici 2020.");
                bdd.insererDepeche("10:30","Imaginez ce qu","Imaginez ce qui peut être fait avec une plateforme Web ouvert embarquée sur de petits périphériques, connectés les uns aux autres ");
                bdd.insererDepeche("11:00","Peut-être que","Peut-être que vous voulez contrôler votre maison à partir de votre montre ou téléphones ? Les possibilités sont sans limites ");
                bdd.insererDepeche("11:30","L’IoT est le rés","L’IoT est le réseau d’objets physiques, périphériques, véhicules, buildings et d'autres éléments dans lesquels sont intégrés des puces électroni");
                bdd.insererDepeche("11:40","Avec l’IoT, les o","Avec l’IoT, les objets peuvent être détectés et contrôlés à distance à travers l'infrastructure réseau existante. ");
                bdd.insererDepeche("12:30","Cela crée de","Cela crée des opportunités pour une intégration plus directe entre le monde physique et les systèmes informatiques, et résultant en une meilleure efficacité, précision et un avantage économique.");
*/

                try{
                    DepecheFeedParser parser = new DepecheFeedParser();
                    ActualiteFeedParser actualiteParser = new ActualiteFeedParser();
                    depecheFeeds = parser.parse();
                    actualiteFeeds = actualiteParser.parse();

                    bdd.vider();
                    for (DepecheFeed msg : depecheFeeds){
                        bdd.insererDepeche(msg.getDate(), msg.getTitle(), msg.getDescription());
                        //Log.i(msg.getDate(),msg.getTitle()+ msg.getDescription());
                    }
                    for (ActualiteFeed msg : actualiteFeeds) {
                        bdd.insererActualite(msg.getDate(), msg.getTitle(), msg.getLink().toString(), msg.getDescription(), msg.getCategorie(), "0");
                    }
                } catch (Throwable t){
                    Log.e("OrangeNews",t.getMessage(),t);
                }


                arrayListDepeches = bdd.listerDepechesArray();

                adapteurDepeche = new AdapteurDepeche(foo, arrayListDepeches);
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        listViewDepeches.setAdapter(adapteurDepeche);
                    }
                });
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(foo, SettingsActivity.class);
            foo.startActivity(intent);
        } else if (id == R.id.action_actualiser) {
            new Thread() {
                public void run() {
                    try{
                        DepecheFeedParser parser = new DepecheFeedParser();
                        ActualiteFeedParser actualiteParser = new ActualiteFeedParser();
                        depecheFeeds = parser.parse();
                        actualiteFeeds = actualiteParser.parse();

                        bdd.vider();
                        for (DepecheFeed msg : depecheFeeds){
                            bdd.insererDepeche(msg.getDate(), msg.getTitle(), msg.getDescription());
                            //Log.i(msg.getDate(),msg.getTitle()+ msg.getDescription());
                        }
                        for (ActualiteFeed msg : actualiteFeeds) {
                            bdd.insererActualite(msg.getDate(), msg.getTitle(), msg.getLink().toString(), msg.getDescription(), msg.getCategorie(), "0");
                        }
                    } catch (Throwable t){
                        Log.e("OrangeNews",t.getMessage(),t);
                    }


                    arrayListDepeches = bdd.listerDepechesArray();

                    adapteurDepeche = new AdapteurDepeche(foo, arrayListDepeches);
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            listViewDepeches.setAdapter(adapteurDepeche);
                        }
                    });
                }
            }.start();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = new Intent(foo, ActualiteListActivity.class);

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_politique) {
            intent.putExtra(ActualiteListActivity.ARG_ACTUALITE_CATEGORIE, "P");
        } else if (id == R.id.nav_lupourvous) {
            intent.putExtra(ActualiteListActivity.ARG_ACTUALITE_CATEGORIE, "L");
        } else if (id == R.id.nav_enimage) {
            intent.putExtra(ActualiteListActivity.ARG_ACTUALITE_CATEGORIE, "N");
        } else if (id == R.id.nav_culture) {
            intent.putExtra(ActualiteListActivity.ARG_ACTUALITE_CATEGORIE, "C");
        } else if (id == R.id.nav_economie) {
            intent.putExtra(ActualiteListActivity.ARG_ACTUALITE_CATEGORIE, "E");
        } else if (id == R.id.nav_societe) {
            intent.putExtra(ActualiteListActivity.ARG_ACTUALITE_CATEGORIE, "S");
        } else if (id == R.id.nav_sport) {
            intent.putExtra(ActualiteListActivity.ARG_ACTUALITE_CATEGORIE, "O");
        } else if (id == R.id.nav_interview) {
            intent.putExtra(ActualiteListActivity.ARG_ACTUALITE_CATEGORIE, "I");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        foo.startActivity(intent);

        return true;
    }
}
