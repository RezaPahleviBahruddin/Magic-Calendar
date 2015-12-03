package magsoft.magic_calendar;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
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
		listView.setAdapter(new CustomAdapter());
	}
	
	class CustomAdapter extends BaseAdapter{

		Cursor c;
		JadwalTable jadwal;
		
		public CustomAdapter() {
			jadwal.open();
			c = jadwal.getAll();
			jadwal.close();
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
			
			TextView tv = (TextView) findViewById(R.id.txtEdit);
			Cursor item = (Cursor) getItem(position);
			tv.setText(item.getString(1));
			
			return v;
		}
		
	}
}