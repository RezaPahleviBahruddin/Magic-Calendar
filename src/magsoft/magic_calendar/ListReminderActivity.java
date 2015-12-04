package magsoft.magic_calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import magsoft.magic_calendar.db.JadwalTable;

public class ListReminderActivity extends Activity implements OnScrollListener{
	private int firstVisibleItem = 0;
	private Cursor c;
	private TextView txtFixed;
	private int month = 0;
	private SimpleDateFormat dateFormat;
	private Calendar cal;
	private final String[] monthInString= {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober",
			"November", "Desember"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_list_schedule);
		
		ListView listView = (ListView) findViewById(R.id.listSchedule);
		
		txtFixed = (TextView) findViewById(R.id.txtFixed);
		
		JadwalTable jadwal = new JadwalTable(getApplicationContext());
		jadwal.open();
		c = jadwal.getAll();
//		jadwal.close();
		CustomAdapter ca = new CustomAdapter(c);
		dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		cal = Calendar.getInstance();
		c.moveToFirst();
		
		try {
			cal.setTime(dateFormat.parse(c.getString(3)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		txtFixed.setText(monthInString[cal.get(Calendar.MONTH)]);
		
		listView.setAdapter(ca);
		
		listView.setOnScrollListener(this);
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

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if (this.firstVisibleItem != firstVisibleItem){
			Log.d("Magsoft", "firstVisibleItem -> "+firstVisibleItem+"; visibleItemCount -> "+visibleItemCount+"; totalItemCount -> "+totalItemCount);
			this.firstVisibleItem = firstVisibleItem;
			c.moveToPosition(firstVisibleItem);
			
			try {
				cal.setTime(dateFormat.parse(c.getString(3)));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			if (month != cal.get(Calendar.MONTH)) {
				Log.d("Magsoft", "month -> "+cal.get(Calendar.MONTH));
				month = cal.get(Calendar.MONTH);
				txtFixed.setText(monthInString[month]);
			}
		}
	}
}