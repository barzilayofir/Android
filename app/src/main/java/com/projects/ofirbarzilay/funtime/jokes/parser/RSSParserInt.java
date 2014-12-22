package com.projects.ofirbarzilay.funtime.jokes.parser;

import java.util.ArrayList;

/**
 * Created by Ofir.Barzilay on 15/12/2014.
 */
public interface RSSParserInt {
    public ArrayList<RSSItem> getRssItemList();
    public void processFeed(String pageURL);

}
