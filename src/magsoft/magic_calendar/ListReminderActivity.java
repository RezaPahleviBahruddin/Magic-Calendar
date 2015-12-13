package magsoft.magic_calendar;

import java.util.Calendar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class ListReminderActivity extends FragmentActivity implements View.OnLayoutChangeListener{

	ViewPager mPager;
	ScheduleAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_list_schedule);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		

		mPager = (ViewPager) findViewById(R.id.schedulePager);
		mAdapter = new ScheduleAdapter(getSupportFragmentManager());
		
		mPager.setAdapter(mAdapter);
		mPager.setCurrentItem(mAdapter.getCount()/2);
		
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
}