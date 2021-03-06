package magsoft.magic_calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import magsoft.magic_calendar.db.JadwalTable;

public class CalendarAdapter extends BaseAdapter {
	private Context mContext;

	private java.util.Calendar month;
	public GregorianCalendar pmonth; // calendar instance for previous month
	/**
	 * calendar instance for previous month for getting complete view
	 */
	public GregorianCalendar pmonthmaxset;
	private GregorianCalendar selectedDate;
	int firstDay;
	int maxWeeknumber;
	int maxP;
	int calMaxP;
	int lastWeekDay;
	int leftDays;
	int mnthlength;
	int temporaryMonth = -1;
	String itemvalue, curentDateString;
	DateFormat df;
	boolean firstSelected;

	private ArrayList<String> items;
	public static List<String> dayString;
	private View previousView;
	
	private long timeSelected = 0;

	public CalendarAdapter(Context c, GregorianCalendar monthCalendar) {
		CalendarAdapter.dayString = new ArrayList<String>();
		Locale.setDefault(Locale.US);
		month = monthCalendar;
		selectedDate = (GregorianCalendar) monthCalendar.clone();
		mContext = c;
		month.set(GregorianCalendar.DAY_OF_MONTH, 1);
		this.items = new ArrayList<String>();
		df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		curentDateString = df.format(selectedDate.getTime());
		refreshDays();
	}

	public void setItems(ArrayList<String> items) {
		for (int i = 0; i != items.size(); i++) {
			if (items.get(i).length() == 1) {
				items.set(i, "0" + items.get(i));
			}
		}
		this.items = items;
	}

	public int getCount() {
		return dayString.size();
	}

	public Object getItem(int position) {
		return dayString.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new view for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		TextView dayView;
		if (convertView == null) { // if it's not recycled, initialize some
			// attributes
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.calendar_item, null);

		}
		dayView = (TextView) v.findViewById(R.id.date);
		// separates daystring into parts.
		String[] separatedTime = dayString.get(position).split("-");
		// taking last part of date. ie; 2 from 2012-12-02
		String gridvalue = separatedTime[2].replaceFirst("^0*", "");
		// checking whether the day is in current month or not.
		if (position < firstDay - 1 || Integer.parseInt(separatedTime[1]) > month.get(Calendar.MONTH) + 1) {
			// setting offdays to white color.
			dayView.setTextColor(Color.rgb(200, 200, 200));
			dayView.setClickable(false);
			dayView.setFocusable(false);
		} else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
			// i dont really understand what this is block for
			
			dayView.setTextColor(Color.rgb(100,100,100));
			dayView.setClickable(false);
			dayView.setFocusable(false);
		} else {
			// setting curent month's days in blue color.
			dayView.setTextColor(Color.rgb(100, 100, 100));
		}
		
		// call function for checking if this is a holiday or not
		if ( !(position < firstDay - 1 || Integer.parseInt(separatedTime[1]) > month.get(Calendar.MONTH) + 1) ){
			if (isHoliday(gridvalue	) == 1){
				dayView.setTextColor(Color.RED);
			}
			else if (isHoliday(gridvalue) == 2){
				dayView.setTextColor(Color.BLUE);
			}
		}

		if (dayString.get(position).equals(curentDateString)) {
			setSelected(v);
			previousView = v;
		} else {
			v.setBackgroundResource(R.drawable.list_item_background);
		}
		dayView.setText(gridvalue);

		// create date string for comparison
		String date = dayString.get(position);

		if (date.length() == 1) {
			date = "0" + date;
		}
		String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
		if (monthStr.length() == 1) {
			monthStr = "0" + monthStr;
		}

		// show icon if date is not empty and it exists in the items array
//		ImageView iw = (ImageView) v.findViewById(R.id.date_icon);
//		if (date.length() > 0 && items != null && items.contains(date)) {
//			iw.setVisibility(View.VISIBLE);
//		} else {
//			iw.setVisibility(View.INVISIBLE);
//		}
		return v;
	}

	/**
	 * I don't know what happened. But every time i select the date, this function
	 * is executed two times! Its a lot confusing.
	 * 
	 * So because i can't think clearly what really happened, im here created a
	 * variable to store the time this function is executed. If the currentTime and 
	 * the lastTime is divided by less than a second, it means the setselected method
	 * doesn't need to do something.
	 * */
	public View setSelected(View view) {
		if (previousView != null) {
			previousView.setBackgroundResource(R.drawable.list_item_background);
			
			TextView previousDate = (TextView) previousView.findViewById(R.id.date);
			previousDate.setTextColor(Color.rgb(100, 100, 100));
		}
		previousView = view;
		view.setBackgroundResource(R.drawable.calendar_cel_selectl);
		
		TextView currentItem = (TextView) view.findViewById(R.id.date);
		
		// check if this is not the day out of the month
		if (currentItem.getCurrentTextColor() == Color.rgb(200, 200, 200)){
			return view;
		}
		
		currentItem.setTextColor(Color.rgb(240, 240, 240));
		
		// check for the first time its executed
		if (firstSelected){
			firstSelected = false;
			return view;
		}
		
		/*
		 * this checking is for preventing the method from being
		 * executed two times
		 */
		long currentTime = System.currentTimeMillis();
		if (currentTime-timeSelected < 1000){
			return view;
		}
		timeSelected = currentTime;
		
		// do your stuff here
//		Log.d("Magsoft", "month -> "+month.get(Calendar.MONTH)+";date -> "+currentItem.getText().toString());
		
		// open list schedule activity based on the selected date
		Intent intent = new Intent(mContext, ListReminderActivity.class);
		intent.putExtra("year", month.get(Calendar.YEAR));
		intent.putExtra("month", month.get(Calendar.MONTH));
		intent.putExtra("day", currentItem.getText().toString());
		intent.putExtra("type", "daily");
		view.getContext().startActivity(intent);
		
		return view;
	}

	public void refreshDays() {
		// clear items
		items.clear();
		dayString.clear();
		Locale.setDefault(Locale.US);
		pmonth = (GregorianCalendar) month.clone();
		// month start day. ie; sun, mon, etc
		firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);
		// finding number of weeks in current month.
		maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
		// allocating maximum row number for the gridview.
		mnthlength = maxWeeknumber * 7;
		maxP = getMaxP(); // previous month maximum day 31,30....
		calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...
		/**
		 * Calendar instance for getting a complete gridview including the three
		 * month's (previous,current,next) dates.
		 */
		pmonthmaxset = (GregorianCalendar) pmonth.clone();
		/**
		 * setting the start date as previous month's required date.
		 */
		pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);

		/**
		 * filling calendar gridview.
		 */
		for (int n = 0; n < mnthlength; n++) {

			itemvalue = df.format(pmonthmaxset.getTime());
			pmonthmaxset.add(GregorianCalendar.DATE, 1);
			dayString.add(itemvalue);

		}
		
		firstSelected = true;
	}

	private int getMaxP() {
		int maxP;
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMinimum(GregorianCalendar.MONTH)) {
			pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
					month.getActualMaximum(GregorianCalendar.MONTH), 1);
		} else {
			pmonth.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) - 1);
		}
		maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		return maxP;
	}
	
	public Calendar getCalendar(){
		return this.month;
	}
	
	
	/**
	 * this function is for checking whether this is holiday or not.
	 * or if there is a schedule on a day.
	 * 
	 * this will return 
	 * 1 if the day is holiday
	 * 2 if the day is not holiday but there is some schedules on it
	 * 0 if there is nothing on the day
	 * */
	public int isHoliday(String day){
		// create the instance of table
		JadwalTable table = new JadwalTable(mContext);
		
		// open the connection
		table.open();
		
		// clone the current instance of calendar and set the day to be the value passed
		Calendar calendar = (Calendar) month.clone();
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
		
		// get the data from table by passing the calendar
		Cursor c = table.getByDay(calendar);
		
		boolean sunday = calendar.get(Calendar.DAY_OF_WEEK) == 1;
		if (sunday){
			return 1;
		}
		// looping through the cursor
		if (c.moveToFirst()){
			do{
				// if it is a holiday then return 1;
				
				// also chechk whether its a sunday
				
				if (c.getString(JadwalTable.FIELD_IS_HOLIDAY).equals("yes")){
					return 1;
				}
			}
			while(c.moveToNext());
		}
		
		// if there is no holiday but reminder, then return 2
		if (c.getCount() > 0){
			return 2;
		}
		
		return 0;
	}

}
