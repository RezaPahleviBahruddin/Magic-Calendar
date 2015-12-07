package magsoft.magic_calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import magsoft.magic_calendar.db.JadwalTable;

public class EditReminderActivity extends Activity implements OnClickListener{
	EditText editTitle, editKeterangan, editDate;
	Button btnAdd;
	JadwalTable jadwal;
	
	private DatePickerDialog toDatePickerDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_reminder);
		
		editTitle = (EditText) findViewById(R.id.editTitle);
		editKeterangan = (EditText) findViewById(R.id.editKeterangan);
		editDate = (EditText) findViewById(R.id.editDate);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		
		editDate.setOnClickListener(this);
		editDate.setFocusable(false);
		
		String title = getIntent().getExtras().getString(JadwalTable.KEY_TITLE);
		String description = getIntent().getExtras().getString(JadwalTable.KEY_DESCRIPTION);
		String date = getIntent().getExtras().getString(JadwalTable.KEY_DATE);
		
		editTitle.setText(title);
		editKeterangan.setText(description);
		editDate.setText(date);
		btnAdd.setText("Update");
		
		jadwal = new JadwalTable(getApplicationContext());
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
	
	public void btnAddClick(View view){
		/**
		 * Update the data to the table
		 * */
		jadwal.open();
		
		String title = editTitle.getText().toString();
		String description = editKeterangan.getText().toString();
		String date = editDate.getText().toString();
		
		if (title.equals("") || date.equals("")) {
			Toast.makeText(this, "Input error", Toast.LENGTH_SHORT).show();
			return;
		}
		
		jadwal.update((Integer) getIntent().getExtras().get(JadwalTable.KEY_ID), title, description, date);
		
		jadwal.close();
		
		editTitle.setText("");
		editKeterangan.setText("");
		editDate.setText("");
		Toast.makeText(this, "Data sudah diperbarui", Toast.LENGTH_SHORT).show();
		
		
		
		// back to previous activity
		finish();
	}

	@Override
	public void onClick(View v) {
		if (v == editDate){
			toDatePickerDialog.show();
		}
	}
	
}
