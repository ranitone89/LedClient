package com.example.ledclient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btRed = findViewById(R.id.buttonRed);
        btRed.setBackgroundColor(Color.RED);

        Button btBlue = findViewById(R.id.buttonBlue);
        btBlue.setBackgroundColor(Color.BLUE);

        Button btGreen = findViewById(R.id.buttonGreen);
        btGreen.setBackgroundColor(Color.GREEN);

    }

    /**
     * Send red color to rapsberry
     * @param view
     */
    public void sendRedColor(View view)
    {
        // Do something in response to button click
        Toast.makeText(this, "Clicked Red Button", Toast.LENGTH_LONG).show();
        TextView txView = findViewById(R.id.textView2);
        txView.setText("COLOR RED");

        MyClientTask clientTask = new MyClientTask("192.168.2.101", 4445);

        Log.i("myInfoTag","################### " + clientTask.dstAddress);
        System.out.print("################### Port: " + clientTask.dstPort);

        clientTask.execute();
    }
    /**
     * Send blue color to rapsberry
     * @param view
     */
    public void sendBlueColor(View view)
    {
        // Do something in response to button click
        Toast.makeText(this, "Clicked Bue Button", Toast.LENGTH_LONG).show();
        TextView txView = findViewById(R.id.textView2);
        txView.setText("COLOR BLUE");
    }

    /**
     * Send green color to rapsberry
     * @param view
     */
    public void sendGreenColor(View view)
    {
        // Do something in response to button click
        Toast.makeText(this, "Clicked Green Button", Toast.LENGTH_LONG).show();
        TextView txView = findViewById(R.id.textView2);
        txView.setText("COLOR GREEN");
    }

    public class MyClientTask extends AsyncTask<Void, Void, Void> {

        String dstAddress;
        int dstPort;
        String response;

        MyClientTask(String addr, int port){
            dstAddress = addr;
            dstPort = port;
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            Log.i("myInfoTag", "################### DoInBackground");
            try {
                Socket socket = new Socket(dstAddress, dstPort);
                InputStream inputStream = socket.getInputStream();

                Log.i("myInfoTag", "################### Response: " + inputStream.toString());

                ByteArrayOutputStream byteArrayOutputStream =
                        new ByteArrayOutputStream(1024);
                byte[] buffer = new byte[1024];

                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                //Log.i("myInfoTag", "################### Response: " + response.toString());
                Log.i("myInfoTag", "################### Response: ");
                socket.close();
                response = byteArrayOutputStream.toString("UTF-8");
                Log.i("myInfoTag", "################### Response: " + response);


            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //textResponse.setText(response);
            super.onPostExecute(result);
        }

    }
}
