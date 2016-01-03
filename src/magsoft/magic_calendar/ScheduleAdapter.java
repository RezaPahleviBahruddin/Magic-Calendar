package magsoft.magic_calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ScheduleAdapter extends FragmentStatePagerAdapter{

	private List<ScheduleFragment> fragments;
	private Calendar c = Calendar.getInstance();
	
	public ScheduleAdapter(FragmentManager fm) {
		super(fm);
		
		fragments = new ArrayList<ScheduleFragment>();
		
		// initial fragments
		for (int i = 0; i < 600; i++) {
			ScheduleFragment frag = new ScheduleFragment();
			fragments.add(frag);
		}

		

		// if the type is daily
		if (ListReminderActivity.type.equals("daily")){
			// initial content of fragments
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 300);
			for (int i = 0; i < getCount(); i++){
				fragments.get(i).setCalendar(c);
				c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
			}
			return;
		}
		
		// initial content of fragments
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 300);
		for (int i = 0; i < getCount(); i++){
			fragments.get(i).setCalendar(c);
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		}
	}
	
	@Override
	public Fragment getItem(int position) {
		ScheduleFragment fragment;
		
		if (position >= fragments.size()){
			ScheduleFragment frag = new ScheduleFragment();
			fragments.add(frag);
		}
		
		fragment = fragments.get(position);
		
		return fragment;
	}

	@Override
	public int getCount() {
		return fragments.size();
	}
	
	public Calendar getCalendar(){
		return c;
	}

}
