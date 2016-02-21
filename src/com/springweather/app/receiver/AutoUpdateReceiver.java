package com.springweather.app.receiver;

import com.springweather.app.service.AutoUpdateService;

import android.content.Context;
import android.content.Intent;

public class AutoUpdateReceiver extends android.content.BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent i = new Intent(context, AutoUpdateService.class);
		context.startService(i);
	}

}
