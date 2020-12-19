package com.cv.coreservice;

import android.os.AsyncTask;
import android.os.Trace;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MessageSocket  {
    private final String ipAddress ="192.168.100.125";
    private final  int port = 10003;
    private String message;
    private static final String TAG = "MessageSocket";

    public MessageSocket(String message) {
        this.message = message;
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            Socket s;
            try {

                Log.i(TAG,"Socket Start.");
                s = new Socket(ipAddress,port);
                DataOutputStream dot =new DataOutputStream(s.getOutputStream());
                dot.writeUTF(message);
                dot.flush();
                dot.close();
                s.close();
                Log.i(TAG,"Socket End.");


            } catch (IOException e) {
                Log.i(TAG,e.getMessage());
            }
        }
    });
    thread.start();
    }
}
