package magsoft.magic_calendar.bc_handlers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import magsoft.magic_calendar.MainActivity;
import magsoft.magic_calendar.R;

public class OnAlarm extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		/**
		 * Building the notification
		 * */
		
		NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(context);
		notifBuilder.setSmallIcon(R.drawable.ic_launcher);
		notifBuilder.setContentTitle("Magic Calendar");
		notifBuilder.setContentText("Hari ini adalah hari libur! :)");
		
		Intent i = new Intent(context, MainActivity.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addParentStack(MainActivity.class);
		
		stackBuilder.addNextIntent(i);
		PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		
		notifBuilder.setContentIntent(pendingIntent);
		
		NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notifManager.notify(0, notifBuilder.build());
	}

}
