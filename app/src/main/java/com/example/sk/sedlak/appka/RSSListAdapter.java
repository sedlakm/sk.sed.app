package com.example.sk.sedlak.appka;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RSSListAdapter extends ArrayAdapter<RssItem>{

	Context context;
	private static final int layoutResourceId = R.layout.list_rss_item;
	
	
	public RSSListAdapter(Context context) {
		super(context, layoutResourceId);
		this.context = context;
	}
		
	
	//Try
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ItemHolder holder = null;
		
		
		if(row == null) {
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new ItemHolder();
			holder.listImage = (ImageView)row.findViewById(R.id.list_image);
			holder.listItem = (TextView)row.findViewById(R.id.list_item);
			holder.listSubItem = (TextView)row.findViewById(R.id.list_sub_item);
			holder.listSubItemTwo = (TextView)row.findViewById(R.id.list_sub_item_two);
			
			row.setTag(holder);
			
		}
		else
			
		{
			holder = (ItemHolder)row.getTag();
		}
		
		RssItem rssitem = getItem(position);
		//holder.listImage.getImageResources(rssitem.enclosure.url);
		holder.listItem.setText(rssitem.title);
		holder.listSubItem.setText(rssitem.description);
		holder.listSubItemTwo.setText(rssitem.link);
		
		//if (rssitem.hasImageLink()){
		//	new ThumbnailTask(position, rssitem.getImageUrl(), holder).execute();
		//	}
	
		return row;
	}
	
	static class ItemHolder {
		TextView listItem;
		TextView listSubItem;
		TextView listSubItemTwo;
		ImageView listImage;
		int position;
	}
	
	
	
/*	private static class ThumbnailTask extends AsyncTask<Void, Void, Bitmap> {
	    private int mPosition;
	    private ItemHolder mHolder;
	    private String mUrl;
	    
	    
	    public ThumbnailTask(int position, String url, ItemHolder holder) {
	        mPosition = position;
	        mHolder = holder;
	        mUrl = url;
	    }
	 
	    @Override
	    protected Bitmap doInBackground(Void... arg0) {
	        return decodeFile(url, 50  dajaka sirka, 50 dajaka vyska);
	    }
	 
	    @Override
	    protected void onPostExecute(Bitmap bitmap) {
	        if (mHolder.position == mPosition) {
	            mHolder.thumbnail.setImageBitmap(bitmap);
	        }
	    }
	    
	    private Bitmap decodeFile(String imageUrl, int WIDTH, int HIGHT) {
	    try {
	      BitmapFactory.Options o = new BitmapFactory.Options();
	      o.inJustDecodeBounds = true;
	      BitmapFactory.decodeStream(new URL(imageUrl).openStream(), null, o);
	      
	      final int REQUIRED_WIDTH = WIDTH;
	      final int REQUIRED_HIGHT = HIGHT;
	      int scale = 1;
	      
	      while (o.outWidth / scale / 2 >= REQUIRED_WIDTH && o.outHeight / scale / 2 >= REQUIRED_HIGHT)
	        scale *= 2;
	      
	      BitmapFactory.Options o2 = new BitmapFactory.Options();
	      o2.inSampleSize = scale;
	      
	      return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    
	    return null;
	  }
	    
	}*/
}
