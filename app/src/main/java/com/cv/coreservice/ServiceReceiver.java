package com.cv.coreservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class ServiceReceiver extends BroadcastReceiver {
    private static final String TAG = "ServiceReceiver";
    @Override
    public void onReceive(final Context context, Intent intent) {
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
                if(!incomingNumber.isEmpty()){
                    new MessageSocket(incomingNumber);
                    Toast.makeText(context,"incoming Number :"+incomingNumber,Toast.LENGTH_LONG).show();
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);

    }
}