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
		for (int i = 0; i < 300; i++) {
			ScheduleFragment frag = new ScheduleFragment();
			fragments.add(frag);
		}
		
		// initial content of three fragments
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
		for (int i = getCount()/2-1; i < getCount()/2+2; i++){
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
