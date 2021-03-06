package magsoft.magic_calendar;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import magsoft.magic_calendar.db.JadwalTable;

public class ScheduleDetailActivity extends Activity {
	private TextView txtDescription;
	private JadwalTable jadwal;
	private Cursor c;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule_detail);
		
		jadwal = new JadwalTable(getApplicationContext());
		
		txtDescription = (TextView) findViewById(R.id.description);	
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		jadwal.open();
		
		c = jadwal.getById((Integer) getIntent().getExtras().get(JadwalTable.KEY_ID) );
		
		if (!c.moveToFirst()){
			return;
		}
		
		String title = c.getString(JadwalTable.FIELD_TITLE);
		setTitle(title);
		
		String description = c.getString(JadwalTable.FIELD_DESCRIPTION);
		
		
		if (description == "" || description == null || description.length() == 0) {
			description = "No Description";
		}
		
		txtDescription.setText(description);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		jadwal.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		if (c.getString(JadwalTable.FIELD_TYPE).equals("user")){
			getMenuInflater().inflate(R.menu.schedule_detail, menu);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_edit) {
			Intent intent = new Intent(getApplicationContext(), EditReminderActivity.class);
			
			intent.putExtra(JadwalTable.KEY_ID, (Integer) getIntent().getExtras().get(JadwalTable.KEY_ID));
			intent.putExtra(JadwalTable.KEY_TITLE, (String) getIntent().getExtras().get(JadwalTable.KEY_TITLE));
			intent.putExtra(JadwalTable.KEY_DESCRIPTION, (String) getIntent().getExtras().get(JadwalTable.KEY_DESCRIPTION));
			intent.putExtra(JadwalTable.KEY_DATE, (String) getIntent().getExtras().get(JadwalTable.KEY_DATE));
			
			startActivity(intent);
			return true;
		}
		else if (id == R.id.action_delete){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure you want to delete this?");
			builder.setPositiveButton("Delete", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface arg0, int arg1){
					JadwalTable jadwal = new JadwalTable(getApplicationContext());
					jadwal.open();
					jadwal.delete((Integer) getIntent().getExtras().get(JadwalTable.KEY_ID));
					jadwal.close();
					
					finish();
				}
			});
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			});
			builder.setCancelable(true);
			builder.show();
		}
		return super.onOptionsItemSelected(item);
	}
}
