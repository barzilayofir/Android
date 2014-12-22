package com.projects.ofirbarzilay.funtime.jokes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projects.ofirbarzilay.funtime.R;
import com.projects.ofirbarzilay.funtime.jokes.parser.RSSItem;
import com.projects.ofirbarzilay.funtime.jokes.parser.RSSParser;
import com.projects.ofirbarzilay.funtime.jokes.parser.RSSParserInt;

import java.util.ArrayList;
import java.util.Iterator;

public class JokesActivity extends Activity {

    private RSSParserInt parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);
        //parser = new RSSHandler();
        parser = new RSSParser();
        new MyTask().execute();
    }

    private void draw() {
        final LinearLayout jokesView = (LinearLayout) findViewById(R.id.jokes_rss_view);
        ArrayList<RSSItem> rssItemList = parser.getRssItemList();

        Iterator<RSSItem> iterator = rssItemList.iterator();
        while (iterator.hasNext()) {
            final RSSItem rssItem = iterator.next();

            jokesView.addView(createTextView(rssItem.getTitle()));
            //jokesView.addView(createTextView(rssItem.getDate()));
            jokesView.addView(createTextView(rssItem.getDescription()));

//            TextView linkView = createTextView(rssItem.getLink());
//            Linkify.addLinks(linkView, Linkify.WEB_URLS);

//            final WebView webView = new WebView(this);
//            int viewId = webView.generateViewId();
//            webView.setId(viewId);
//            webView.setVisibility(View.INVISIBLE);

            Button showJokeView = new Button(this);
            showJokeView.setText(getString(R.string.show_joke));
            //TextView showJokeView = createTextView(getString(R.string.show_joke));

            showJokeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rssItem.getLink()));
                    startActivity(browserIntent);
                }
            });

            jokesView.addView(showJokeView);
           // jokesView.addView(webView);
        }


    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        //textView.setTextSize(28);
        textView.setTypeface(null, Typeface.BOLD);
        return textView;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jokes, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String url1 = "http://www.nifgashim.net/nif/Utils.RSS.Jokes.cls";
            String url = "http://www.bdihot.co.il/ctype/clean/feed/";
            String url2 = "http://www.dop.co.il/sites/rss/srss.php";
            parser.processFeed(url);
            //parser.processFeed(url1);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            draw();
            super.onPostExecute(result);
        }
    }
}
