package magsoft.magic_calendar;

import java.sql.Date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import magsoft.magic_calendar.R;

public class StaticCal extends LinearLayout{
	LinearLayout header;
	ImageView btnPrev;
	ImageView btnNext;
	TextView txtDate;
	GridView grid;

	public StaticCal(Context context) {
		super(context);
		initControl(context);
	}
	
	private void initControl(Context context){
		LayoutInflater inflater = (LayoutInflater)
				context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		
		inflater.inflate(R.layout.control, this);
		
		header = (LinearLayout)findViewById(R.id.calendar_header);
		btnPrev = (ImageView)findViewById(R.id.calendar_prev_button);
		btnNext = (ImageView)findViewById(R.id.calendar_next_button);
		txtDate = (TextView)findViewById(R.id.calendar_date_display);
		grid = (GridView)findViewById(R.id.calendar_grid);
		
	}
	
	private void updateCalendar(){
		ArrayList<Date> cells = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DAY_OF_MONTH , 1);
		int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK - 1);
		
		calendar.add(Calendar.DAY_OF_MONTH, monthBeginningCell);
		
		while(cells.size() < calendar.getMaximum(Calendar.DAY_OF_MONTH)){
			cells.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		((CalendarAdapter)grid.getAdapter()).updateData(cells);
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMM yyyy");
		txtDate.setText(sdf.format(currentDate.getTime()));
	}
}
