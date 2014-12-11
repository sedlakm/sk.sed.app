package com.example.sk.sedlak.appka;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RssActivity extends Activity{
	
	TextView titleTextView, contentTextView, linkTextView;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rss_activity);
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
	
		TextView titleTextView = (TextView) findViewById(R.id.rss_activity_title);
		TextView contentTextView = (TextView) findViewById(R.id.rss_activity_text);
		TextView linkTextView = (TextView) findViewById(R.id.rss_activity_link);
		
		RssItem item = (RssItem)getIntent().getExtras().getSerializable("RSS_ITEM");
		
		titleTextView.setText(item.getTitle());
		titleTextView.setTextSize(40);
		titleTextView.setBackgroundColor(0xff888888);
		contentTextView.setText(item.getDescription());
		contentTextView.setTextSize(20);
		contentTextView.setBackgroundColor(0xffcccccc);
		linkTextView.setText(item.getLink());
		linkTextView.setTextSize(12);

	}
}
