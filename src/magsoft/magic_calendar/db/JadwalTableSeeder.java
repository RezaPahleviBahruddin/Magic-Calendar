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
		{"Hari Dharma Wanita Nasional", "", "2015-08-05", "system", "yes", "no"},
		{"Hari Ulang Tahun ASEAN", "", "2015-08-08", "system", "yes", "no"},
		{"Hari Pramuka", "", "2015-08-14", "system", "yes", "no"},
		{"Hari Kemerdekaan Indonesia", "Hari Proklamasi Kemerdekaan Republik Indonesia sejak tahun 1945", "2015-08-17", "system", "yes", "yes"},
		{"Hari Maritim Nasional", "", "2015-08-21", "system", "yes", "no"},
		
		// September
		{"Hari Buruh", "", "2015-09-01", "system", "yes", "no"},
		{"Hari Olahraga Nasional", "", "2015-09-09", "system", "yes", "no"},
		{"Hari Radio Republik Indonesia", "Sering juga disingkat Hari RRI", "2015-09-11", "system", "yes", "no"},
		{"Hari Peringatan Serangan 11 September", "", "2015-09-11", "system", "yes", "no"},
		{"Hari Palang Merah Indonesia", "", "2015-09-17", "system", "yes", "no"},
		{"Hari Perhubungan Nasional", "", "2015-09-17", "system", "yes", "no"},
		{"Hari Perdamaian Dunia", "", "2015-09-21", "system", "yes", "no"},
		{"Hari Raya Idul Adha", "", "2015-09-24", "system", "no", "yes"},
		{"Hari Tani", "", "2015-09-24", "system", "yes", "no"},
		{"Hari Jantung Sedunia", "", "2015-09-29", "system", "yes", "no"},
		{"Hari Peringatan G30SPKI", "Gerakan 30 September 1965", "2015-09-30", "system", "yes", "no"},
		
		// Oktober 2015 and the statics
		{"Hari Kesaktian Pancasila", "", "2015-10-01", "system", "yes", "no"},
		{"Hari Bakti Nasional", "", "2015-10-02", "system", "yes", "no"},
		{"Hari Hewan Sedunia", "", "2015-10-04", "system", "yes", "no"},
		{"Hari Tentara Nasional Indonesia", "TNI", "2015-10-05", "system", "yes", "no"},
		{"Satu Muharram", "Hari Tahun Baru Hijriah 1437", "2015-10-14", "system", "no", "yes"},
		{"Hari Pangan Sedunia", "", "2015-10-16", "system", "yes", "no"},
		{"Hari Santri Nasional", "", "2015-10-22", "system", "yes", "no"},
		{"Hari Dokter Nasional", "", "2015-10-24", "system", "yes", "no"},
		{"Hari Perserikatan Bangsa-Bangsa", "Atau Hari PBB", "2015-10-24", "system", "yes", "no"},
		{"Hari Listrik Nasional", "", "2015-10-27", "system", "yes", "no"},
		{"Hari Sumpah Pemuda", "", "2015-10-28", "system", "yes", "no"},
		
		// November 2015 and the statics
		{"Hari Inovasi Indonesia", "", "2015-11-01", "system", "yes", "no"},
		{"Hari Pahlawan", "", "2015-11-10", "system", "yes", "no"},
		{"Hari Ayah Nasional", "", "2015-11-12", "system", "yes", "no"},
		{"Hari Diabetes International", "", "2015-11-14", "system", "yes", "no"},
		{"Hari Anak-Anak Sedunia", "", "2015-11-20", "system", "yes", "no"},
		{"Hari Pohon", "", "2015-11-21", "system", "yes", "no"},
		{"Hari Guru", "", "2015-11-25", "system", "yes", "no"},
		{"Hari Menanam Pohon Sedunia", "", "2015-11-28", "system", "yes", "no"},
		
		// Desember 2015 and the statics
		{"Hari AIDS sedunia", "", "2015-12-01", "system", "yes", "no"},
		{"Hari Aramada dan Anti Korupsi", "", "2015-12-09", "system", "yes", "no"},
		{"Pilkada Serentak", "", "2015-12-09", "system", "no", "yes"},
		{"Hari Hak Asasi Manusia", "", "2015-12-10", "system", "yes", "no"},
		{"Hari Transmigrasi", "", "2015-12-12", "system", "yes", "no"},
		{"Hari Nusantara", "", "2015-12-13", "system", "yes", "no"},
		{"Hari Infanteri", "", "2015-12-15", "system", "yes", "no"},
		{"Hari Bela Negara", "", "2015-12-19", "system", "yes", "no"},
		{"Hari Ibu", "", "2015-12-22", "system", "yes", "no"},
		{"Cuti Bersama Malam Natal", "", "2015-12-24", "system", "yes", "yes"},
		{"Hari Maulid Nabi Muhammad SAW", "", "2015-12-24", "system", "no", "yes"},
		{"Hari Natal", "", "2015-12-25", "system", "yes", "yes"},
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
