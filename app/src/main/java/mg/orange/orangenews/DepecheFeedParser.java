package mg.orange.orangenews;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 15/02/2016.
 */
public class DepecheFeedParser {

    static String feedUrlString = "http://10.0.2.2:8000/feed/depeches/";

    // names of the XML tags
    static final String RSS = "rss";
    static final String CHANNEL = "channel";
    static final String ITEM = "item";

    static final String PUB_DATE = "pubDate";
    static final String DESCRIPTION = "description";
    static final String LINK = "link";
    static final String TITLE = "title";

    private final URL feedUrl;

    protected DepecheFeedParser(){
        try {
            this.feedUrl = new URL(feedUrlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    protected InputStream getInputStream() {
        try {
            return feedUrl.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DepecheFeed> parse() {
        final DepecheFeed currentDepecheFeed = new DepecheFeed();
        RootElement root = new RootElement(RSS);
        final List<DepecheFeed> depecheFeeds = new ArrayList<DepecheFeed>();
        Element itemlist = root.getChild(CHANNEL);
        Element item = itemlist.getChild(ITEM);
        item.setEndElementListener(new EndElementListener(){
            public void end() {
                depecheFeeds.add(currentDepecheFeed.copy());
            }
        });
        item.getChild(TITLE).setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                currentDepecheFeed.setTitle(body);
            }
        });
        item.getChild(LINK).setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                currentDepecheFeed.setLink(body);
            }
        });
        item.getChild(DESCRIPTION).setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                currentDepecheFeed.setDescription(body);
            }
        });
        item.getChild(PUB_DATE).setEndTextElementListener(new EndTextElementListener(){
            public void end(String body) {
                currentDepecheFeed.setDate(body);
            }
        });
        try {
            Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return depecheFeeds;
    }
}
