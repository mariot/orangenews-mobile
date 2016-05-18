package mg.orange.orangenews;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static mg.orange.orangenews.DepecheFeedParser.*;

/**
 * Created by User on 15/02/2016.
 */
public class RssHandler extends DefaultHandler {
    private List<DepecheFeed> depecheFeeds;
    private DepecheFeed currentDepecheFeed;
    private StringBuilder builder;

    public List<DepecheFeed> getDepecheFeeds(){
        return this.depecheFeeds;
    }
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        builder.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        super.endElement(uri, localName, name);
        if (this.currentDepecheFeed != null){
            if (localName.equalsIgnoreCase(TITLE)){
                currentDepecheFeed.setTitle(builder.toString());
            } else if (localName.equalsIgnoreCase(LINK)){
                currentDepecheFeed.setLink(builder.toString());
            } else if (localName.equalsIgnoreCase(DESCRIPTION)){
                currentDepecheFeed.setDescription(builder.toString());
            } else if (localName.equalsIgnoreCase(PUB_DATE)){
                currentDepecheFeed.setDate(builder.toString());
            } else if (localName.equalsIgnoreCase(ITEM)){
                depecheFeeds.add(currentDepecheFeed);
            }
            builder.setLength(0);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        depecheFeeds = new ArrayList<DepecheFeed>();
        builder = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String name,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, name, attributes);
        if (localName.equalsIgnoreCase(ITEM)){
            this.currentDepecheFeed = new DepecheFeed();
        }
    }
}