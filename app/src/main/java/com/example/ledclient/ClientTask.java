package com.example.led;

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

public class ClientTask extends AsyncTask<Void, Void, Void> {

    String ipAddress;
    int myPort;
    String response;

    ClientTask(String addr, int port){
        ipAddress = addr;
        myPort = port;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        try {
            Socket socket = new Socket(ipAddress, myPort);
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream =
                    new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            socket.close();
            response = byteArrayOutputStream.toString("UTF-8");

            Log.i("myInfoTag","################### " + response);

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
