package com.projects.ofirbarzilay.funtime.jokes.parser;

import android.graphics.Bitmap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ofir.Barzilay on 14/12/2014.
 */
public class RSSParser implements RSSParserInt {
    //private String url = "http://www.nasa.gov/rss/dyn/image_of_the_day.rss";

    private String url = "http://www.bdihot.co.il/ctype/clean/feed/";
    private String url1 = "http://www.dop.co.il/sites/rss/srss.php";

    private boolean inUrl = false;
    private boolean inTitle = false;
    private boolean inDescription = false;
    private boolean inItem = false;
    private boolean inDate = false;
    private boolean inLink = false;
    private Bitmap image = null;
    private RSSItem currentRSSItem;
    private ArrayList<RSSItem> rssItemList = new ArrayList<RSSItem>();
    private String suffix = "לבדיחה";

    public void processFeed(String pageURL) {
        try {
            if (pageURL != null) {
                url = pageURL;
            }

            String rssString = getRSSString(pageURL);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            //factory.setNamespaceAware(false);

            //xpp.setInput(new StringReader(rssString));

            InputStream inputStream = new URL(url).openStream();
            xpp.setInput(inputStream, null);

            readRss(xpp);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getRSSString(String pageURL) {
        InputStream in = null;
        String rssFeed = null;
        try {
            URL url = new URL(pageURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            in = conn.getInputStream();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            for (int count; (count = in.read(buffer)) != -1; ) {
                out.write(buffer, 0, count);
            }
            byte[] response = out.toByteArray();
            rssFeed = new String(response);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rssFeed;
    }

    private List<String> readRss1(XmlPullParser parser)
            throws XmlPullParserException, IOException {

        List<String> items = new ArrayList<>();
        //parser.require(XmlPullParser.START_TAG, null, "rss");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("channel")) {
                items.addAll(readChannel(parser));
            } else {
                skip(parser);
            }
        }
        return items;
    }

    public void readRss(XmlPullParser parser)
    {
        try
        {

            boolean insideItem = false;

            // Returns the type of current event: START_TAG, END_TAG, etc..
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    String name = parser.getName();
                    if (name.equals("channel")) {
                        readChannel(parser);
                    }
                }

                eventType = parser.next(); /// move to next element
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private List<String> readChannel(XmlPullParser parser)
            throws IOException, XmlPullParserException {
        List<String> items = new ArrayList<>();
        parser.require(XmlPullParser.START_TAG, null, "channel");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("item")) {
                items.add(readItem(parser));
            } else {
                skip(parser);
            }
        }
        return items;
    }

    private String readItem(XmlPullParser parser) throws XmlPullParserException, IOException {
        currentRSSItem = new RSSItem();
        rssItemList.add(currentRSSItem);
        String result = null;
        parser.require(XmlPullParser.START_TAG, null, "item");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("title")) {
                result = readTitle(parser);
            } else if (name.equals("description")) {
                result = readDescription(parser);
            } else if (name.equals("link")) {
                result = readLink(parser);
            } else {
                skip(parser);
            }
        }
        return result;
    }

    // Processes title tags in the feed.
    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, null, "title");
        String title = readText(parser);
        currentRSSItem.setTitle(title);
        parser.require(XmlPullParser.END_TAG, null, "title");
        return title;
    }

    private String readDescription(XmlPullParser parser) throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, null, "description");
        String text = readText(parser);


        text = text.replaceAll(suffix, "...");

        currentRSSItem.setDescription(text);
        parser.require(XmlPullParser.END_TAG, null, "description");
        return text;
    }

    private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {

        parser.require(XmlPullParser.START_TAG, null, "link");
        String title = readText(parser);
        currentRSSItem.setLink(title);
        parser.require(XmlPullParser.END_TAG, null, "link");
        return title;
    }

    private String readText(XmlPullParser parser)
            throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }


    public ArrayList<RSSItem> getRssItemList() {
        return rssItemList;
    }


}

