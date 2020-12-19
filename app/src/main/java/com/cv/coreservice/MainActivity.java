package com.cv.coreservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText ipAddress;
    private EditText port;
    private DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler = new DatabaseHandler(this);
        ipAddress = (EditText) findViewById(R.id.txtIpAddress);
        port = (EditText) findViewById(R.id.txtPort);
        Button btnConnect = (Button) findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip = ipAddress.getText().toString();
                Integer p = Integer.parseInt(port.getText().toString());
                if(!ip.isEmpty() && p>0) {
                    NetworkModel networkModel = new NetworkModel();
                    networkModel.setId(1);
                    networkModel.setIpAddress(ip);
                    networkModel.setPort(p);
                    databaseHandler.save(networkModel);
                }
            }
        });
    }
}