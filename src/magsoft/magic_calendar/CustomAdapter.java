package magsoft.magic_calendar;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import magsoft.magic_calendar.db.JadwalTable;

public class CustomAdapter extends BaseAdapter{

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
		ImageView imgLock = (ImageView) v.findViewById(R.id.lock);
		
		tv.setText(cursor.getString(1));
		
		String description = cursor.getString(JadwalTable.FIELD_DESCRIPTION);
		
		if (description.length() > 20){
			description = description.substring(0, 20);
		}
		else if (description.length() < 1) {
			description = "No description";
		}
		
		tv2.setText(description);
		
		if (!cursor.getString(JadwalTable.FIELD_TYPE).equals("system")) {
			imgLock.setImageResource(R.drawable.edit_black);
		}
		
		return v;
	}
}
