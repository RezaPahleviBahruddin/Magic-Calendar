package magsoft.magic_calendar;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import magsoft.magic_calendar.db.JadwalTable;

public class ListReminderActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_list_schedule);
		
		ListView listView = (ListView) findViewById(R.id.listSchedule);
		
		JadwalTable jadwal = new JadwalTable(getApplicationContext());
		jadwal.open();
		Cursor c = jadwal.getAll();
//		jadwal.close();
		CustomAdapter ca = new CustomAdapter(c);
		listView.setAdapter(ca);
	}
	
	class CustomAdapter extends BaseAdapter{

		Cursor c;
		
		public CustomAdapter(Cursor c) {
			this.c = c;
		}
		
		@Override
		public int getCount() {
			return c.getCount();
		}

		@Override
		public Object getItem(int position) {
			return c.moveToPosition(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			LayoutInflater inflater = getLayoutInflater();
			
			if (v== null) {
				v= inflater.inflate(R.layout.list_schedule, parent, false);
			}
			getItem(position);
			
			TextView tv = (TextView) v.findViewById(R.id.txtEdit);
			TextView tv2 = (TextView) v.findViewById(R.id.txtDescription);
			tv.setText(c.getString(1));
			tv2.setText(c.getString(3));
			return v;
		}
		
	}
}