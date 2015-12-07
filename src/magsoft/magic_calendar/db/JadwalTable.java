package magsoft.magic_calendar.db;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	public static final String KEY_TYPE = "type";
	public static final String KEY_STATIC = "static";
	
	public static final int FIELD_ID = 0, FIELD_TITLE = 1, FIELD_DESCRIPTION = 2, FIELD_DATE = 3,
			FIELD_TYPE = 4, FIELD_STATIC = 5;
	
	private static final String DB_NAME = "magic_calendar";
	private static final String DB_TABLE_NAME = "jadwal";
	private static int DB_VERSION_NUMBER = 2;
	
	private static final String QUERY_CREATE_TABLE = "create table "+DB_TABLE_NAME+
			" ("+KEY_ID+" integer primary key autoincrement,"+
			" "+KEY_TITLE+" text not null,"+
			" "+KEY_DESCRIPTION+" text null,"+
			" "+KEY_DATE+" date not null,"+
			" "+KEY_TYPE+" text not null,"+
			" "+KEY_STATIC+" text not null"+
			");";
	
	private Context context;
	private SQLiteDatabase db;
	private DBHelper dbHelper;
	
	private SimpleDateFormat dateFormat;

	public JadwalTable(Context context) {
		this.context = context;
		dbHelper = new DBHelper(this.context);
		dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
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
		return insert(title, description, date, "user", "no");
	}
	
	public long insert(String title, String description, String date, String type, String isStatic) throws SQLException{
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_DESCRIPTION, description);
		values.put(KEY_DATE, date);
		values.put(KEY_TYPE, type);
		values.put(KEY_STATIC, isStatic);
		
		return db.insert(DB_TABLE_NAME, null, values);
	}
	
	public long update(int id, String title, String description, String date) throws SQLException{
		return update(id, title, description, date, "user", "no");
	}
	
	public long update(int id, String title, String description, String date, String type, String isStatic) throws SQLException{
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_DESCRIPTION, description);
		values.put(KEY_DATE, date);
		values.put(KEY_TYPE, type);
		values.put(KEY_STATIC, isStatic);
		
		return db.update(DB_TABLE_NAME, values, KEY_ID+"='"+id+"'", null);
	}
	
	public Cursor getById(int id){
		return db.query(DB_TABLE_NAME, new String[]{
				KEY_ID, KEY_TITLE, KEY_DESCRIPTION, KEY_DATE, KEY_TYPE, KEY_STATIC
		}, KEY_ID+"='"+id+"'", null, null, null, KEY_DATE+" DESC");
	}
	
	public Cursor getAll(){
		return db.query(DB_TABLE_NAME, new String[]{
				KEY_ID, KEY_TITLE, KEY_DESCRIPTION, KEY_DATE, KEY_TYPE, KEY_STATIC
		}, null, null, null, null, KEY_DATE+" DESC");
	}
	
	public Cursor getToday(){
		Date dt = new Date();
		return db.query(DB_TABLE_NAME, new String[]{
				KEY_ID, KEY_TITLE, KEY_DESCRIPTION, KEY_DATE, KEY_TYPE, KEY_STATIC
		}, KEY_DATE+"='"+dateFormat.format(dt)+"'", null, null, null, KEY_ID+" DESC");
	}

	public Cursor getMonth(Calendar c) {
		String year = c.get(Calendar.YEAR)+"";
		String month = (c.get(Calendar.MONTH)+1)+"";
		
		if (month.length() < 2){
			month = "0"+month;
		}
		
		return db.query(DB_TABLE_NAME, new String[]{
				KEY_ID, KEY_TITLE, KEY_DESCRIPTION, KEY_DATE, KEY_TYPE, KEY_STATIC
		}, KEY_DATE+" LIKE '"+year+"-"+month+"-%'", null, null, null, KEY_ID+" DESC");
	}

	public long delete(int id) {
		return db.delete(DB_TABLE_NAME, KEY_ID+"='"+id+"'", null);
	}
}