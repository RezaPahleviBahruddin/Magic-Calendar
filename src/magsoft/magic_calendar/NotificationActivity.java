package magsoft.magic_calendar;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import magsoft.magic_calendar.db.JadwalTable;

public class NotificationActivity extends Activity implements OnItemClickListener{

	private ListView list;
	private CustomAdapter adapter;
	JadwalTable jadwal;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_notification);
		
		jadwal = new JadwalTable(getApplicationContext());
		list = (ListView) findViewById(R.id.listNotification);
		
		list.setOnItemClickListener(this);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		// cancel the notification with id 0
        NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancel(0);
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		
		
		jadwal.open();
		
		adapter = new CustomAdapter(jadwal.getToday());
		list.setAdapter(adapter);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		jadwal.close();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(view.getContext(), ScheduleDetailActivity.class);
		Cursor currentItem = (Cursor) adapter.getItem(position);
		
		intent.putExtra(JadwalTable.KEY_ID, currentItem.getInt(0));
		intent.putExtra(JadwalTable.KEY_TITLE, currentItem.getString(1));
		intent.putExtra(JadwalTable.KEY_DESCRIPTION, currentItem.getString(2));
		intent.putExtra(JadwalTable.KEY_DATE, currentItem.getString(3));
		
		startActivity(intent);
	}
}
