package magsoft.magic_calendar;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.widget.EditText;
import magsoft.magic_calendar.db.JadwalTable;

public class AddReminderActivity extends Activity{

	private EditText editTitle, editDescription, editDate;
	private JadwalTable jadwal;
	private SimpleDateFormat dateFormat;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_reminder);
		
		instantiasi();
		Date dt = new Date();
		
		editDate.setHint(dateFormat.format(dt));
	}
	
	private void instantiasi(){
		editTitle = (EditText) findViewById(R.id.editTitle);
		editDescription = (EditText) findViewById(R.id.editKeterangan);
		editDate = (EditText) findViewById(R.id.editDate);
		
		jadwal = new JadwalTable(this);
		dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
	}
	
	public void btnAddClick(View v){
		
		/**
		 * Insert the data to the table
		 * */
		jadwal.open();
		
		String title = editTitle.getText().toString();
		String description = editDescription.getText().toString();
		String date = editDate.getText().toString();
		
		jadwal.insert(title, description, date);
		
		jadwal.close();
		
		editTitle.setText("");
		editDescription.setText("");
		editDate.setText("");
	}
}
