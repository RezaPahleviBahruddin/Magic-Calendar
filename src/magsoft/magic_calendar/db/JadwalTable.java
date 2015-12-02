package magsoft.magic_calendar.db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class JadwalTable {
	public static final String KEY_ID = "id";
	public static final String KEY_TITLE = "title";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_DATE = "date";
	
	private static final String DB_NAME = "magic_calendar";
	private static final String DB_TABLE_NAME = "jadwal";
	private static int DB_VERSION_NUMBER = 1;
	
	private static final String QUERY_CREATE_TABLE = "create table "+DB_TABLE_NAME+
			" ("+KEY_ID+" integer primary key autoincrement,"+
			" "+KEY_TITLE+" text not null,"+
			" "+KEY_DESCRIPTION+" text not null,"+
			" "+KEY_DATE+" datetime not null);";
	
	private Context context;
	private SQLiteDatabase db;
	private DBHelper dbHelper;
	
	public JadwalTable(Context context) {
		this.context = context;
		dbHelper = new DBHelper(context);
	}
	
	private static class DBHelper extends SQLiteOpenHelper{
		public DBHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION_NUMBER);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(QUERY_CREATE_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists "+DB_TABLE_NAME);
			onCreate(db);
		}
	}
	
	public JadwalTable open() throws SQLException{
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		db.close();
	}
	
	public long insert(String title, String description, String date) throws SQLException{
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_DESCRIPTION, description);
		values.put(KEY_DATE, date);
		
		return db.insert(DB_TABLE_NAME, null, values);
	}
	
	public Cursor getAll(){
		return db.query(DB_TABLE_NAME, new String[]{
				KEY_ID, KEY_TITLE, KEY_DESCRIPTION, KEY_DATE
		}, null, null, null, null, KEY_DATE);
	}
}