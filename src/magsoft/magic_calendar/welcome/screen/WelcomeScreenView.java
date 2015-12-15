package magsoft.magic_calendar.welcome.screen;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeScreenView extends LinearLayout{

	private ViewPager pager;
	private WelcomeScreenAdapter adapter;
	
	public WelcomeScreenView(Context context) {
		super(context);
		
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		setOrientation(VERTICAL);
		
		pager = new ViewPager(getContext());
		pager.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		adapter = new WelcomeScreenAdapter(getContext());
		
		addView(pager);
		
		pager.setAdapter(adapter);
	}

}
