package magsoft.magic_calendar.db;

import android.content.ContentValues;
import android.content.Context;

public class JadwalTableSeeder {
	public static String data[][] = new String[][]{
		// title, description, date (yyyy-mm-dd), type (system, user), is_static ('yes', 'no'), is_holiday('yes','no')
		
		// January 2015 and the statics
		{"Tahun Baru Masehi", "", "2015-01-01", "system", "yes", "yes"},
		{"Maulid Nabi Muhammad", "", "2015-01-03", "system", "no", "no"},
		{"Hari Korps Wanita Angkatan Laut", "", "2015-01-05", "system", "yes", "no"},
		{"Hari Gerakan Satu Juta Pohon", "", "2015-01-10", "system", "yes", "no"},
		{"Hari Tritura", "", "2015-01-10", "system", "yes", "no"},
		{"Hari Dharma Samudra", "Hari peristiwa laut dan samudra", "2015-01-15", "system", "yes", "no"},
		{"Hari Gizi dan Makanan", "", "2015-01-25", "system", "yes", "no"},
		{"Hari Kusta International", "", "2015-01-31", "system", "yes", "no"},
		
		// Februari 2015 and the statics
		{"Hari Peristiwa Kapal Tujuh", "", "2015-02-05", "system", "yes", "no"},
		{"Hari Kavaleri", "", "2015-02-09", "system", "yes", "no"},
		{"Hari Pers Nasional", "", "2015-02-09", "system", "yes", "no"},
		{"Hari Peringatan Pembela Tanah Air (PETA)", "", "2015-02-14", "system", "yes", "no"},
		{"Hari Valentine", "Atau juga sering disebut Hari Kasih Sayang. Tradisi ini adalah tradisi orang-orang barat.", "2015-02-2014", "system", "yes", "no"},
		{"Tahun Baru Imlek", "", "2015-02-19", "system", "no", "yes"},
		{"Hari Pekerja Indonesia", "", "2015-02-20", "system", "yes", "no"},
		{"Hari Istiqlal", "", "2015-02-22", "system", "yes", "no"},
		{"Hari Gizi Nasional", "", "2015-02-28", "system", "yes", "no"},
		
		// Maret 2015 and the statics
		{"Hari Kehakiman Nasional", "", "2015-03-01", "system", "yes", "no"},
		{"Hari Peringatan SERUM DIY", "Hari peringatan serangan umum yang terjadi di Yogyakarta", "2015-03-01", "system", "yes", "no"},
		{"Hari Kostrad", "Hari Komando Strategi Angkatan Darat", "2015-03-06", "system", "yes", "no"},
		{"Hari Perempuan Nasional", "", "2015-03-08", "system", "yes", "no"},
		{"Hari Musik Nasional", "", "2015-03-09", "system", "yes", "no"},
		{"Hari Persatuan Artisa Film Indonesia", "", "2015-03-10", "system", "yes", "no"},
		{"Hari Supersemar", "Hari Surat Perintah Sebelas Maret", "2015-03-11", "system", "yes", "no"},
		{"Hari Raya Nyepi", "", "2015-03-22", "system", "no", "yes"},
		{"Hari Air Sedunia", "", "2015-03-23", "system", "yes", "no"},
		{"Hari Meteorologi Dunia", "", "2015-03-24", "system", "yes", "no"},
		{"Hari Peringatan Bandung Lautan Api", "", "2015-03-27", "system", "yes", "no"},
		{"Hari Filateli Indonesia", "", "2015-03-29", "system", "yes", "no"},
		{"Hari Film Nasional", "", "2015-03-30", "system", "yes", "no"}
	};
	
	public static void seed(Context context){
		JadwalTable jadwal = new JadwalTable(context);
		jadwal.open();
		for (int i = 0; i < data.length; i++) {
			jadwal.insert(data[i][0], data[i][1], data[i][2], data[i][3], data[i][4], data[i][5]);
		}
		jadwal.close();
	}
}
