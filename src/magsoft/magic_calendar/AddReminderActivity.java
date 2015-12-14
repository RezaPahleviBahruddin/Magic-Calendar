package magsoft.magic_calendar;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import magsoft.magic_calendar.db.JadwalTable;

public class AddReminderActivity extends Activity implements OnClickListener{

	private EditText editTitle, editDescription, editDate;
	private JadwalTable jadwal;
	private SimpleDateFormat dateFormat;
	private DatePickerDialog toDatePickerDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_reminder);
		
		instantiasi();
		Date dt = new Date();
		
		editDate.setHint(dateFormat.format(dt));
		
		Calendar newCalendar = Calendar.getInstance();
		
		toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				String date = year+"";
				
				date += "-";
				
				if (++monthOfYear < 10){
					date += "0";
				}
				
				date += monthOfYear;
				date += "-";
				
				if (dayOfMonth < 10){
					date+= "0";
				}
				
				date += dayOfMonth;
				
				editDate.setText(date);
			}
		}, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
	}
	
	private void instantiasi(){
		editTitle = (EditText) findViewById(R.id.editTitle);
		editDescription = (EditText) findViewById(R.id.editKeterangan);
		editDate = (EditText) findViewById(R.id.editDate);
		editDate.setFocusable(false);
		
		jadwal = new JadwalTable(this);
		dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		
		editDate.setOnClickListener(this);
	}
	
	public void btnAddClick(View v){
		
		/**
		 * Insert the data to the table
		 * */
		jadwal.open();
		
		String title = editTitle.getText().toString();
		String description = editDescription.getText().toString();
		String date = editDate.getText().toString();
		
		if (title.equals("") || date.equals("")) {
			Toast.makeText(this, "Input error", Toast.LENGTH_SHORT).show();
			return;
		}
		
		jadwal.insert(title, description, date);
		
		jadwal.close();
		
		editTitle.setText("");
		editDescription.setText("");
		editDate.setText("");
		Toast.makeText(this, "Data sudah masuk", Toast.LENGTH_SHORT).show();
		
		// back to main activity
		finish();
	}

	@Override
	public void onClick(View v) {
		if (v == editDate) {
			toDatePickerDialog.show();
		}
	}
}
