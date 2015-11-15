package com.example.lexa.myapplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Net {
    InputStream inputStream = null;
    FileOutputStream fileOutputStream = null;

    public void runnne() {
        try {
            URL url = new URL("http://kiparo.ru/t/pub.json");
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            int status = connection.getResponseCode();
            System.out.println("day2");
            if (status == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                File file = new File("pub.json");
                fileOutputStream = new FileOutputStream(file);
                int byteRead = -1;
                byte[] bufer = new byte[4096];
                while ((byteRead = inputStream.read(bufer)) != -1)
                    fileOutputStream.write(bufer, 0, byteRead);
            }

        } catch (Exception e) {
            System.out.println("day1");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
