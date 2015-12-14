package magsoft.magic_calendar.bc_handlers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import magsoft.magic_calendar.MainActivity;
import magsoft.magic_calendar.NotificationActivity;
import magsoft.magic_calendar.R;
import magsoft.magic_calendar.db.JadwalTable;

public class OnAlarm extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		/**
		 * Building the notification
		 * */
		JadwalTable jadwal = new JadwalTable(context);
		jadwal.open();
		Cursor c = jadwal.getToday();
		
		NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(context);
		notifBuilder.setSmallIcon(R.drawable.cal_10);
		notifBuilder.setContentTitle("Magic Calendar");
		
		if (c.getCount() > 1){
			notifBuilder.setContentText("Hari ini ada "+c.getCount()+" agenda");
		}
		else if (c.getCount() == 1){
			c.moveToFirst();
			notifBuilder.setContentText(""+c.getString(JadwalTable.FIELD_TITLE));
		}
		else {
			notifBuilder.setContentText("Semoga hari ini berjalan lancar! :)");
		}
		
		Intent i = new Intent(context, NotificationActivity.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addParentStack(MainActivity.class);
		
		stackBuilder.addNextIntent(i);
		PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		
		notifBuilder.setContentIntent(pendingIntent);
		
		NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notifManager.notify(0, notifBuilder.build());
		
		jadwal.close();
	}

}
