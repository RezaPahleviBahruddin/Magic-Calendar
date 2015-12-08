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
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.Toast;
import magsoft.magic_calendar.db.JadwalTable;
import magsoft.magic_calendar.service.MyAlarm;
import android.os.Build;

public class MainActivity extends Activity {
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        
        HashSet<Date> events = new HashSet<Date>();
		events.add(new Date());

		CalendarView cv = ((CalendarView)findViewById(R.id.calendar_view));
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
        else if (id == R.id.action_show_schedules){
        	JadwalTable jadwal = new JadwalTable(this);
        	jadwal.open();
        	
        	Cursor c = jadwal.getAll();
        	
        	if (c.moveToFirst()){
        		do{
        			Toast.makeText(this, c.getString(1), Toast.LENGTH_SHORT).show();
        		}
        		while(c.moveToNext());
        	}
        	
        	jadwal.close();
        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onStart(){
    	super.onStart();
    	
    	final String PREF_NAME = "MagicCalendarSettings";
    	SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
    	
    	if (settings.getBoolean("my_first_time", true)) {
			// ketika aplikasi pertama kali dijalankan
    		// register alarm
    		MyAlarm myAlarm = new MyAlarm(this);
    		myAlarm.start();
    		
    		Toast.makeText(this, "Hello this is my first time!", Toast.LENGTH_LONG).show();
    		
    		settings.edit().putBoolean("my_first_time", false).commit();
		}
    	else{
    		Toast.makeText(this, "Hello again!", Toast.LENGTH_LONG).show();
    	}
    }

}
