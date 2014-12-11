package com.example.sk.sedlak.appka;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimePickerFragment extends DialogFragment implements
		TimePickerDialog.OnTimeSetListener {

	boolean fired = false;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);

		return new TimePickerDialog(getActivity(), this, hour, minute,
				!DateFormat.is24HourFormat(getActivity()));
	}

	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		if (fired) {
			fired = false;
		} else {
			String time = String.format("%02d:%02d", hourOfDay, minute);
			Toast.makeText(getActivity(), "Alarm has been set to: " + time,
					Toast.LENGTH_LONG).show();

			Calendar currentCalendar = Calendar.getInstance();
			Calendar alarmCalendar = Calendar.getInstance();
			alarmCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
			alarmCalendar.set(Calendar.MINUTE, minute);
			alarmCalendar.set(Calendar.SECOND, 0);

			if (currentCalendar.getTimeInMillis() > alarmCalendar
					.getTimeInMillis()) {
				alarmCalendar.add(Calendar.DATE, 1);
			}

			final int RQS_1 = 1;
			Intent intent = new Intent(getActivity(), AlarmReceiver.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(
					getActivity(), RQS_1, intent, 0);
			AlarmManager alarmManager = (AlarmManager) getActivity()
					.getSystemService(Context.ALARM_SERVICE);
			alarmManager.set(AlarmManager.RTC_WAKEUP,
					alarmCalendar.getTimeInMillis(), pendingIntent);
			// info about time
			System.out.println(currentCalendar.getTimeInMillis());
			System.out.println(alarmCalendar.getTimeInMillis());
			fired = true;
		}
	}
	
	public void onCancelPress(){
		
		final int RQS_1 = 1;
		Intent intent = new Intent(getActivity(), AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				getActivity(), RQS_1, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getActivity()
				.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(pendingIntent);
				
	}

}
