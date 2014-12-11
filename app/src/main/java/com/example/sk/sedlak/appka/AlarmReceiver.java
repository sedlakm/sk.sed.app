package com.example.sk.sedlak.appka;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Toast.makeText(arg0, "Alarm! Alarm! Alarm!", Toast.LENGTH_LONG).show();

		Vibrator v = (Vibrator) arg0.getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(1000);

		Uri notification = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_ALARM);
		final Ringtone r = RingtoneManager.getRingtone(arg0.getApplicationContext(),
				notification);
		r.play();
		final Handler handler = new Handler();
	    handler.postDelayed(new Runnable() {
	        @Override
	        public void run() {
	            if (r.isPlaying())
	                r.stop();
	        }
	    }, 25000);
	}
	
	public static void onDestroy(Context arg0, Intent arg1) {
		Vibrator v = (Vibrator) arg0.getSystemService(Context.VIBRATOR_SERVICE);
		v.vibrate(0);

		Uri notification = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_ALARM);
		final Ringtone r = RingtoneManager.getRingtone(arg0.getApplicationContext(),
				notification);
		r.play();
		final Handler handler = new Handler();
	    handler.postDelayed(new Runnable() {
	        @Override
	        public void run() {
	            if (r.isPlaying())
	                r.stop();
	        }
	    }, 0);
	}
}