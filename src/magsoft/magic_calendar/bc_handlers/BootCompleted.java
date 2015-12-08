package magsoft.magic_calendar.bc_handlers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import magsoft.magic_calendar.service.MyAlarm;

public class BootCompleted extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		MyAlarm myAlarm = new MyAlarm(context);
		myAlarm.start();
	}

}
