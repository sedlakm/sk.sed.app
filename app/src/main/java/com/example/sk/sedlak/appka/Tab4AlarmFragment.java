package com.example.sk.sedlak.appka;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Tab4AlarmFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.tab4_alarm_fragment, container,
				false);

		Button buttonCancel = (Button) view.findViewById(R.id.cancelalarm);
		buttonCancel.setOnClickListener(new Button.OnClickListener() {
		
		@Override 
		public void onClick(View arg0) {
			
			 System.out.println("cancel alarm");
			 final int RQS_1 = 1;
				Intent intent = new Intent(getActivity(), AlarmReceiver.class);
				PendingIntent pendingIntent = PendingIntent.getBroadcast(
						getActivity(), RQS_1, intent, 0);
				AlarmManager alarmManager = (AlarmManager) getActivity()
						.getSystemService(Context.ALARM_SERVICE);
				alarmManager.cancel(pendingIntent);
				
				Toast.makeText(getActivity(), "Alarm has been canceled !",
						Toast.LENGTH_LONG).show();
				
				//TO-DO won't stop the ringtone
				Uri notification = RingtoneManager
						.getDefaultUri(RingtoneManager.TYPE_ALARM);
				final Ringtone r = RingtoneManager.getRingtone(arg0.getContext(),
						notification);
				if (r.isPlaying())
	                r.stop();
				
			 } 
		
		});
		
		return view;

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);

		inflater.inflate(R.menu.tab4_alarm_fragment, menu);
	}

}