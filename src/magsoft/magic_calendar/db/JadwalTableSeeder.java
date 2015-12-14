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
		{"Hari Valentine", "Atau juga sering disebut Hari Kasih Sayang. Tradisi ini adalah tradisi orang-orang barat.", "2015-02-14", "system", "yes", "no"},
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
		{"Hari Film Nasional", "", "2015-03-30", "system", "yes", "no"},
		
		// April 2015 and the statics
		{"Hari Bank Dunia", "", "2015-04-01", "system", "yes", "no"},
		{"Hari Marketing Indonesia", "Sering juga disingkat HAMARI", "2015-04-01", "system", "yes", "no"},
		{"Wafat Isa Al-Masih", "", "2015-04-03", "system", "yes", "yes"},
		{"Hari Paskah", "", "2015-04-5", "system", "yes", "yes"},
		{"Hari Nelayan Nasional", "", "2015-04-06", "system", "yes", "no"},
		{"Hari Kesehatan Internasional", "", "2015-04-07", "system", "yes", "no"},
		{"Hari Penerbangan Nasional", "", "2015-04-09", "system", "yes", "no"},
		{"Hari TNI Angkatan Udara", "", "2015-04-09", "system", "yes", "no"},
		{"Hari Bawa Bekal Nasional", "", "2015-04-12", "system", "yes", "no"},
		{"Hari Kopasus", "Hari Komando Pasukan Khusus", "2015-04-16", "system", "yes", "no"},
		{"Hari Peringatan Konferensi Asia-Afrika", "", "2015-04-18", "system", "yes", "no"},
		{"Hari Hansip", "Hari Pertahanan Sipil", "2015-04-19", "system", "yes", "no"},
		{"Hari Konsumen Nasional", "", "2015-04-20", "system", "yes", "no"},
		{"Hari Kartini", "", "2015-04-21", "system", "yes", "no"},
		{"Hari Bumi", "", "2015-04-22", "system", "yes", "no"},
		{"Hari Buku", "", "2015-04-23", "system", "yes", "no"},
		{"Hari Angkutan Nasional", "", "2015-04-24", "system", "yes", "no"},
		{"Hari Solidaritas Asia-Afrika", "", "2015-04-24", "system", "yes", "no"},
		{"Hari Permasyarakatan Indonesia", "", "2015-04-27", "system", "yes", "no"},
		
		// Mei 2015 and the statics
		{"Hari Buruh Sedunia", "", "2015-05-01", "system", "yes", "yes"},
		{"Hari Pendidikan Nasional", "Sering juga disingkat Hardiknas", "2015-05-02", "system", "yes", "no"},
		{"Hari LSD", "Hari Lembaga Sosial Desa", "2015-05-05", "system", "yes", "no"},
		{"Hari POM TNI", "", "2015-05-11", "system", "yes", "no"},
		{"Kenaikan Yesus Kristus", "", "2015-05-14", "system", "no", "yes"},
		{"Hari Korps Resimen Mahadjaya", "", "2015-05-15", "system", "yes", "no"},
		{"Isra' Mi'raj Nabi Muhammad", "", "2015-05-16", "system", "no", "yes"},
		{"Hari Buku Nasional", "", "2015-05-17", "system", "yes", "no"},
		{"Hari Kebangkitan Nasional", "", "2015-05-20", "system", "yes", "no"},
		{"Hari Peringatan Reformasi", "", "2015-05-21", "system", "yes", "no"},
		{"Hari Penyu Sedunia", "", "2015-05-23", "system", "yes", "no"},
		{"Hari Keluarga", "", "2015-05-29", "system", "yes", "no"},
		{"Hari Memberi", "", "2015-05-30", "system", "yes", "no"},
		{"Hari Tanpa Tembakau Sedunia", "", "2015-05-31", "system", "yes", "no"},
		
		// Juni 2015 and the statics
		{"Hari Lahir Pancasila", "", "2015-06-01", "system", "yes", "no"},
		{"Hari Perlindungan Anak", "", "2015-06-01", "system", "yes", "no"},
		{"Hari Susu Nusantara", "", "2015-06-01", "system", "yes", "no"},
		{"Hari Lingkungan Hidup Sedunia", "", "2015-06-05", "system", "yes", "no"},
		{"Hari Laut Sedunia", "", "2015-06-08", "system", "yes", "no"},
		{"Hari Media Sosial", "", "2015-06-10", "system", "yes", "no"},
		{"Hari Ulang Tahun Kota Jakarta", "", "2015-06-22", "system", "yes", "no"},
		{"Hari Bidan Nasional", "", "2015-06-24", "system", "yes", "no"},
		{"Hari Anti Narkoba", "", "2015-06-26", "system", "yes", "no"},
		{"Hari Keluarga Berencana", "", "2015-06-29", "system", "yes", "no"},
		
		// Juli 2015 and the statics
		{"Hari Koperasi", "", "2015-07-12", "system", "yes", "no"},
		{"Cuti Bersama", "Cuti bersama sebelum Hari Raya Idul Fitri", "2015-07-16", "system", "no", "yes"},
		{"Hari Raya Idul Fitri", "", "2015-07-17", "system", "no", "yes"},
		{"Hari Raya Idul Fitri", "", "2015-07-18", "system", "no", "yes"},
		{"Cuti Bersama", "Cuti bersama sebelum Hari Raya Idul Fitri", "2015-07-19", "system", "no", "yes"},
		{"Cuti Bersama", "Cuti bersama sebelum Hari Raya Idul Fitri", "2015-07-20", "system", "no", "yes"},
		{"Cuti Bersama", "Cuti bersama sebelum Hari Raya Idul Fitri", "2015-07-21", "system", "no", "yes"},
		{"Hari Anak Nasional", "", "2015-07-23", "system", "yes", "no"},
		
		// Agustus 2015 and the statics
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
