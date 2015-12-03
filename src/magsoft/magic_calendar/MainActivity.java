package magsoft.magic_calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashSet;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        
        HashSet<Date> events = new HashSet<Date>();
		events.add(new Date());

		CalendarView cv = ((CalendarView)findViewById(R.layout.c));
		cv.updateCalendar(events);

		// assign event handler
		cv.setEventHandler(new CalendarView.EventHandler()
		{
			@Override
			public void onDayLongPress(Date date)
			{
				// show returned day
				DateFormat df = SimpleDateFormat.getDateInstance();
				Toast.makeText(MainActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
			}
		});


        // cancel the notification with id 0
        NotificationManager notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancel(0);
//        setContentView(R.layout.activity_main);
        setContentView(new StaticCal(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.action_add){
        	Intent i = new Intent(this, AddReminderActivity.class);
        	startActivity(i);
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
