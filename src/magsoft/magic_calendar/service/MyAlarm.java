package magsoft.magic_calendar.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import magsoft.magic_calendar.MainActivity;
import magsoft.magic_calendar.bc_handlers.OnAlarm;

public class MyAlarm {
	
	private AlarmManager alrmManager;
	private Context context;
	private Intent intent;
	private PendingIntent pendIntent;
	
	public MyAlarm(Context context) {
		this.context = context;
		alrmManager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);
		intent = new Intent(context, OnAlarm.class);
		pendIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
	}
	
	public void start(){
		alrmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 
				AlarmManager.INTERVAL_FIFTEEN_MINUTES, AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendIntent);
	}
}
