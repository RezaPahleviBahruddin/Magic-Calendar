package magsoft.magic_calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import magsoft.magic_calendar.db.JadwalTable;

public class EditReminderActivity extends Activity {
	EditText editTitle, editKeterangan, editDate;
	Button btnAdd;
	JadwalTable jadwal;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_reminder);
		
		editTitle = (EditText) findViewById(R.id.editTitle);
		editKeterangan = (EditText) findViewById(R.id.editKeterangan);
		editDate = (EditText) findViewById(R.id.editDate);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		
		String title = getIntent().getExtras().getString(JadwalTable.KEY_TITLE);
		String description = getIntent().getExtras().getString(JadwalTable.KEY_DESCRIPTION);
		String date = getIntent().getExtras().getString(JadwalTable.KEY_DATE);
		
		editTitle.setText(title);
		editKeterangan.setText(description);
		editDate.setText(date);
		
		jadwal = new JadwalTable(getApplicationContext());
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
	
}
