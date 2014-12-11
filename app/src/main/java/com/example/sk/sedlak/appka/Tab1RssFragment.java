package com.example.sk.sedlak.appka;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Tab1RssFragment extends ListFragment {
	private RssReader rssReader = new RssReader();
	private ArrayAdapter<RssItem> arrayAdapter;
	Handler handler = new Handler();
	Runnable refresh;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        arrayAdapter = new RSSListAdapter(getActivity());
        setListAdapter(arrayAdapter);
        

        refresh = new Runnable() {
        	public void run(){
        		refreshList();
        		handler.postDelayed(refresh, 600000);
        	}
        };
        
        refresh.run();
        setHasOptionsMenu(true);
    }
    
  
    
    @Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		System.out.println(arrayAdapter.getItem(position));
		Intent intent = new Intent(getActivity(), RssActivity.class);
		intent.putExtra("RSS_ITEM", arrayAdapter.getItem(position));
		startActivity(intent);
	}



	@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tab1_rss_fragment, menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.refresh){
			refreshList();
		}
    	return false;
    }
    
    @Override
    public ListView getListView() {
    	// TODO Auto-generated method stub
    	return super.getListView();
    }
    
    public void refreshList(){
    	new AsyncTask<Void, Void, Rss>() {
        	@Override
        	protected Rss doInBackground(Void... params) {
        		try{
        			return rssReader.getRss("http://rss.sme.sk/rss/rss.asp?id=frontpage");
        		} catch (RssException e){
        			Log.e(getClass().getName(), "Error during rss retrieval", e);
        			return new Rss();
        		}
        	}
        	
        	@Override
        	protected void onPostExecute(Rss result) {
        		if(result == null || result.getChannel() == null) {
        			Toast.makeText(getActivity(), "Cannot retrieve RSS feeds.", Toast.LENGTH_LONG).show();
        		} else {
        		arrayAdapter.clear();
        		arrayAdapter.addAll(result.getChannel().getItemList());
        		arrayAdapter.notifyDataSetChanged();
        		System.out.println("refreshujem");
        	   	}
        	}	
		}.execute();
    }
    
}
