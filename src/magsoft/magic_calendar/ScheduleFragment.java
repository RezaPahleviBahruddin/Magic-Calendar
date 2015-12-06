package magsoft.magic_calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import magsoft.magic_calendar.db.JadwalTable;

public class ScheduleFragment extends Fragment implements OnScrollListener, AdapterView.OnItemClickListener{
	private Calendar c;
	private View view;
	private final String[] monthInString= {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober",
			"November", "Desember"};
	
	private int firstVisibleItem = 0;
	private Cursor cursor;
	private TextView txtFixed;
	private int month = 0;
	private SimpleDateFormat dateFormat;
	private Calendar cal;
	
	private CustomAdapter ca;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view =inflater.inflate(R.layout.fragment_schedule, container, false);
		this.view = view;
		
		showContent();
		
		return view;
	}

	public void setCalendar(Calendar c) {
		this.c = (Calendar) c.clone();
		
		if (view != null){
			showContent();
		}
	}
	
	public void showContent(){
		TextView txtFixed = (TextView) view.findViewById(R.id.txtFixed);
		
		if (isCalendarSet()){
			txtFixed.setText(monthInString[c.get(Calendar.MONTH)]+" "+c.get(Calendar.YEAR));
			
			ListView listView = (ListView) view.findViewById(R.id.listSchedule);
			
			txtFixed = (TextView) view.findViewById(R.id.txtFixed);
			
			JadwalTable jadwal = new JadwalTable(view.getContext());
			jadwal.open();
			
			cursor = jadwal.getMonth(c);
			if (cursor == null) {
				return;
			}
			
			cursor.moveToFirst();
			
//			jadwal.close();
			ca = new CustomAdapter(cursor);
			listView.setAdapter(ca);
			listView.setOnItemClickListener(this);
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(view.getContext(), ScheduleDetailActivity.class);
		Cursor currentItem = (Cursor) ca.getItem(position);
		
		intent.putExtra(JadwalTable.KEY_ID, currentItem.getInt(0));
		intent.putExtra(JadwalTable.KEY_TITLE, currentItem.getString(1));
		intent.putExtra(JadwalTable.KEY_DESCRIPTION, currentItem.getString(2));
		intent.putExtra(JadwalTable.KEY_DATE, currentItem.getString(3));
		
		startActivity(intent);
	}
	
	public boolean isCalendarSet(){
		return c != null;
	}
	
	public Calendar getCalendar(){
		return c;
	}
	
	class CustomAdapter extends BaseAdapter{

		Cursor cursor;
		
		public CustomAdapter(Cursor c) {
			this.cursor = c;
		}
		
		@Override
		public int getCount() {
			return cursor.getCount();
		}

		@Override
		public Object getItem(int position) {
			cursor.moveToPosition(position);
			return cursor;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			if (v== null) {
				v= inflater.inflate(R.layout.list_schedule, parent, false);
			}
			getItem(position);
			
			TextView tv = (TextView) v.findViewById(R.id.txtEdit);
			TextView tv2 = (TextView) v.findViewById(R.id.txtDescription);
			tv.setText(cursor.getString(1));
			tv2.setText(cursor.getString(3));
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
			cursor.moveToPosition(firstVisibleItem);
			
			try {
				cal.setTime(dateFormat.parse(cursor.getString(3)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
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
