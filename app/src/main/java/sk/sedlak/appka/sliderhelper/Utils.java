package sk.sedlak.appka.sliderhelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Environment;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

public class Utils {
	private Context _context;
	public Utils(Context context) {
		this._context = context;
	}
	
	public ArrayList<String> getFilePaths() {
		ArrayList<String> filePaths = new ArrayList<String>();
		System.out.println(Environment.getExternalStorageDirectory()
				+ File.separator + AppConstant.PHOTO_ALBUM);
		
		File directory = new File(Environment.getExternalStorageDirectory().getPath()
			+ File.separator + AppConstant.PHOTO_ALBUM);
		

		
		if(directory.isDirectory()) {
			File[] listFiles = directory.listFiles();
			if (listFiles.length > 0) {
				for (int i = 0; i < listFiles.length; i++) {
					String filePath = listFiles[i].getAbsolutePath();
					if (IsSupportedFile(filePath)) {
						filePaths.add(filePath);
					}
				}
			} else {
				Toast.makeText(
						_context,
						AppConstant.PHOTO_ALBUM
							+ "is empty. Please load some images in it !",
							Toast.LENGTH_LONG).show();
			}
		} else {
			AlertDialog.Builder alert = new AlertDialog.Builder(_context);
			alert.setTitle("Error!");
			alert.setMessage(AppConstant.PHOTO_ALBUM
					+ " directory path is not valid! Please set the image directory name AppConstant.java class");
			alert.setPositiveButton("OK", null);
			alert.show();
		}
		return filePaths;
	}
	private boolean IsSupportedFile(String filePath) {
		String ext = filePath.substring((filePath.lastIndexOf(".") + 1),
				filePath.length());
		if (AppConstant.FILE_EXTN.contains(ext.toLowerCase(Locale.getDefault())))
			return true;
		else
			return false;
	}
	
	public int getScreenWidth() {
		int columnWidth;
		WindowManager wm = (WindowManager) _context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		
		final Point point = new Point();
		try {
			display.getSize(point);
		} catch (java.lang.NoSuchMethodError ignore) {
			point.x = display.getWidth();
			point.y = display.getHeight();
		}
		columnWidth = point.x;
		return columnWidth;
	}
}
