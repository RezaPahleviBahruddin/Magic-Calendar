package magsoft.magic_calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ListReminderActivity extends FragmentActivity implements View.OnLayoutChangeListener{

	ViewPager mPager;
	ScheduleAdapter mAdapter;
	public static String type;
	
	public static int currentItem = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_schedule);
		
		// check for the type whether its daily or monthly
		if (type == null)
			type = "monthly";
		
		if (getIntent().getExtras() != null && getIntent().getExtras().containsKey("type")){
			type = getIntent().getExtras().getString("type");
		}
		
		mPager = (ViewPager) findViewById(R.id.schedulePager);
		mAdapter = new ScheduleAdapter(getSupportFragmentManager());
		
		if (currentItem != -1)
			currentItem = mAdapter.getCount()/2;
		
		// always listen to the change of current item
		mPager.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
			
			@Override
			public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight,
					int oldBottom) {
				currentItem = mPager.getCurrentItem();
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		mPager.setAdapter(mAdapter);
		
		/**
		 * set the current Item position should be
		 * if there is a calendar on the intent that triggered this activity
		 */
		Bundle b = getIntent().getExtras();
		if (b != null && b.get("year") != null && b.get("month") != null && b.get("day") != null){
			int year = Integer.parseInt(b.get("year").toString());
			int month = Integer.parseInt(b.get("month").toString());
			int day = Integer.parseInt(b.get("day").toString());
			
			Calendar currCal = Calendar.getInstance();
			Calendar comeCal = Calendar.getInstance();
			comeCal.set(year, month, day);
			
			long currDate = currCal.getTimeInMillis();
			long comeDate = comeCal.getTimeInMillis();
			long selisih;
			
			if (type.equals("daily")){
				selisih = TimeUnit.MILLISECONDS.toDays(comeDate-currDate);
			}
			else{
				int diffYear = comeCal.get(Calendar.YEAR) - currCal.get(Calendar.YEAR);
				selisih = diffYear * 12 + month - currCal.get(Calendar.MONTH);
			}
			
			currentItem = mAdapter.getCount()/2 + (int) selisih;
		}
		
		
		mPager.setCurrentItem(currentItem);
		
		mPager.addOnLayoutChangeListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list_reminder_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_add:
			Intent intent = new Intent(getApplicationContext(), AddReminderActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight,
			int oldBottom) {
		int position = mPager.getCurrentItem();
		
		// set content untuk kanan dan kiri fragment
		if (position > 0 ){
			ScheduleFragment currentFragment = (ScheduleFragment) mAdapter.getItem(position);
			ScheduleFragment leftFragment = (ScheduleFragment) mAdapter.getItem(position-1);
			
			if (!leftFragment.isCalendarSet()) {
				Calendar c = (Calendar) currentFragment.getCalendar().clone();
				c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
				leftFragment.setCalendar(c);
			}
		}
		
		if (position < mAdapter.getCount()){
			ScheduleFragment currentFragment = (ScheduleFragment) mAdapter.getItem(position);
			ScheduleFragment rightFragment = (ScheduleFragment) mAdapter.getItem(position+1);
			
			if (!rightFragment.isCalendarSet()) {
				Calendar c = (Calendar) currentFragment.getCalendar().clone();
				c.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
				rightFragment.setCalendar(c);
			}
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		type = null;
	}
}