package de.blinkt.openvpn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import de.blinkt.openvpn.core.ProfileManager;


public class OnBootReceiver extends BroadcastReceiver {

	// Debug: am broadcast -a android.intent.action.BOOT_COMPLETED
	@Override
	public void onReceive(Context context, Intent intent) {

		final String action = intent.getAction();

		if(Intent.ACTION_BOOT_COMPLETED.equals(action)) {
			VpnProfile bootProfile = ProfileManager.getOnBootProfile(context);
			if(bootProfile != null) {
				lauchVPN(bootProfile, context);
			}		
		}
	}

	void lauchVPN(VpnProfile profile,Context context) {
		Intent startVpnIntent = new Intent(Intent.ACTION_MAIN);
		startVpnIntent.setClass(context, LogWindow.class);
		startVpnIntent.putExtra(LogWindow.EXTRA_UUID,profile.getUUIDString());
		startVpnIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		//startVpnIntent.putExtra(LogWindow.EXTRA_HIDELOG, true);

		context.startActivity(startVpnIntent);
	}
}
