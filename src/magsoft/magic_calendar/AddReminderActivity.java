package magsoft.magic_calendar;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;

public class AddReminderActivity extends Activity{

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_reminder);
	}
	
	public void btnAddClick(View v){
		NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this);
		notifBuilder.setSmallIcon(R.drawable.ic_launcher);
		notifBuilder.setContentTitle("Magic Calendar");
		notifBuilder.setContentText("Tombol tambah telah diklik!");
		
		Intent intent = new Intent(this, MainActivity.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(MainActivity.class);
		
		stackBuilder.addNextIntent(intent);
		PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		
		notifBuilder.setContentIntent(pendingIntent);
		
		NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notifManager.notify(0, notifBuilder.build());
	}
}
