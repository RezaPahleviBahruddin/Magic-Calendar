package magsoft.magic_calendar;
import java.util.Calendar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import magsoft.magic_calendar.db.JadwalTable;

public class ScheduleFragment extends Fragment implements AdapterView.OnItemClickListener{
	private Calendar c;
	private View view;
	private final String[] monthInString= {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober",
			"November", "Desember"};
	
	private Cursor cursor;
	
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
}
