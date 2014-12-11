package sk.sedlak.appka.imageadapter;
 
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.example.sk.sedlak.appka.FullScreenViewActivity;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
 
public class GridViewAdapter extends BaseAdapter {
 
    private Activity _activity;
    private ArrayList<String> _filePaths = new ArrayList<String>();
    private int imageWidth;
 
    public GridViewAdapter(Activity activity, ArrayList<String> filePaths,
                           int imageWidth) {
        this._activity = activity;
        this._filePaths = filePaths;
        this.imageWidth = imageWidth;
    }
 
    @Override
    public int getCount() {
        return this._filePaths.size();
    }
 
    @Override
    public Object getItem(int position) {
        return this._filePaths.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(_activity);
        } else {
            imageView = (ImageView) convertView;
        }
 
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setFilePath(_filePaths.get(position));
        imageInfo.setPositionIndex(position);
        imageInfo.setWidth(imageWidth);
        new LoadImageTask(imageView, imageInfo).execute();
 
 
        return imageView;
    }
 
    class OnImageClickListener implements OnClickListener {
 
        int _postion;
 
        public OnImageClickListener(int position) {
            this._postion = position;
        }
 
        @Override
        public void onClick(View v) {
            Intent i = new Intent(_activity, FullScreenViewActivity.class);
            i.putExtra("position", _postion);
            _activity.startActivity(i);
        }
 
    }
 
    private class LoadImageTask extends AsyncTask<Void, Void, Bitmap> {
        private final ImageView imageView;
        private final ImageInfo imageInfo;
 
        private LoadImageTask(ImageView imageView, ImageInfo imageInfo) {
            this.imageView = imageView;
            this.imageInfo = imageInfo;
        }
 
        @Override
        protected Bitmap doInBackground(Void... voids) {
            return decodeFile(imageInfo.getFilePath(), imageInfo.getWidth(), imageInfo.getWidth());
        }
 
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new GridView.LayoutParams(imageInfo.getWidth(), imageInfo.getWidth()));
            imageView.setImageBitmap(bitmap);
 
            imageView.setOnClickListener(new OnImageClickListener(imageInfo.getPositionIndex()));
        }
 
        private Bitmap decodeFile(String filePath, int WIDTH, int HIGHT) {
            try {
 
                File f = new File(filePath);
 
                BitmapFactory.Options o = new BitmapFactory.Options();
                o.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(new FileInputStream(f), null, o);
 
                final int REQUIRED_WIDTH = WIDTH;
                final int REQUIRED_HIGHT = HIGHT;
                int scale = 1;
                while (o.outWidth / scale / 2 >= REQUIRED_WIDTH
                        && o.outHeight / scale / 2 >= REQUIRED_HIGHT)
                    scale *= 2;
 
                BitmapFactory.Options o2 = new BitmapFactory.Options();
                o2.inSampleSize = scale;
                return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
 
    private static class ImageInfo {
        private String filePath;
        private int positionIndex;
        private int width;
 
        public String getFilePath() {
            return filePath;
        }
 
        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }
 
        public int getPositionIndex() {
            return positionIndex;
        }
 
        public void setPositionIndex(int positionIndex) {
            this.positionIndex = positionIndex;
        }
 
        public int getWidth() {
            return width;
        }
 
        public void setWidth(int width) {
            this.width = width;
        }
    }
}