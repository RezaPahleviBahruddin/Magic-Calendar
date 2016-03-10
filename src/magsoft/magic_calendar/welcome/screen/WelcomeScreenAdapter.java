package magsoft.magic_calendar.welcome.screen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import magsoft.magic_calendar.MainActivity;
import magsoft.magic_calendar.db.JadwalTableSeeder;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WelcomeScreenAdapter extends PagerAdapter{

	private Context context;
	private String messages[] = {
		"Selamat datang di Magic Calendar",
		"Dengan aplikasi ini, kamu dapat melihat hari-hari nasional, hari libur, dan sebagainya",
		"Kamu juga dapat membuat hari pengingat kamu sendiri",
		"Mendapatkan notifikasi",
		"Jadwal Akademik Fakultas Teknik",
		"Dan banyak hal seru lainnya"
	};
	private RelativeLayout layout;
	
	public WelcomeScreenAdapter(Context context) {
		this.context = context;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		layout = new RelativeLayout(context);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		layout.setPadding(30,30,30,30);
		
		TextView textView = new TextView(context);
		textView.setTextSize(19);
		textView.setGravity(Gravity.CENTER);
		textView.setLayoutParams(layout.getLayoutParams());
		textView.setText(messages[position]);
		textView.setTextColor(Color.rgb(80, 80, 80));
		
		FrameLayout frameLayout = new FrameLayout(context);
		frameLayout.setLayoutParams(layout.getLayoutParams());
		frameLayout.addView(textView);
		
		if (position == messages.length-1){
			Button btnNext = new Button(context);
			btnNext.setText("Paham");
			btnNext.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			
			RelativeLayout layoutForBtn = new RelativeLayout(context);
			layoutForBtn.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			layoutForBtn.setGravity(Gravity.CENTER_HORIZONTAL);
			layoutForBtn.addView(btnNext);
			
			RelativeLayout another = new RelativeLayout(context);
			another.setLayoutParams(layout.getLayoutParams());
			another.setGravity(Gravity.BOTTOM);
			another.addView(layoutForBtn);
			
			
			layout.addView(another);
			
			// add on click listener to the button
			btnNext.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>(){
						ProgressDialog pd;
						@Override
						protected void onPreExecute() {
							pd = ProgressDialog.show(context, "Instantiate", "Please wait.. this will take a view minute");
							pd.setCancelable(false);
							pd.setIndeterminate(true);
							
							if (!pd.isShowing()){
								pd.show();
							}
						}
						
						@Override
						protected Void doInBackground(Void... params) {
							JadwalTableSeeder.seed(context);
							
							return null;
						}
						
						@Override
						protected void onPostExecute(Void result) {
							if (pd != null){
								pd.dismiss();
							}
							
							// check for wheter the application is on the first time launch or not
					        final String PREF_NAME = "MagicCalendarSettings";
					    	SharedPreferences settings = context.getSharedPreferences(PREF_NAME, 0);
					    	settings.edit().putBoolean("my_first_time", false).commit();
					    	
							Intent intent = new Intent(context, MainActivity.class);
							intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
							context.startActivity(intent);							
						}
					};
					
					task.execute((Void[]) null);
				}
			});
		}
		
		layout.addView(frameLayout);
		container.addView(layout);
		return layout;
	}
	
	@Override
	public int getCount() {
		return messages.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((RelativeLayout) object);
	}

}
