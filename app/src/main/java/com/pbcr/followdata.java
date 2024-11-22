package com.pbcr;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class followdata {
    private static final String TAG = "followdata";

    public void senddata(String datefollowup, String followupno, String status,  String nextdate, String methods,String causeofstatus) {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("http://10.0.2.2/followup.php"); // Ensure this URL is correct and accessible
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // Prepare POST data
                String postData = "followup_date=" + encodeURIComponent(datefollowup) +
                        "&followup_visitno=" + encodeURIComponent(followupno) +
                        "&followup_patient_status=" + encodeURIComponent(status) +
                       "&followup_disease_status	=" + encodeURIComponent(causeofstatus) +
                        "&followup_method=" + encodeURIComponent(methods) +
                        "&followup_last_followup_date=" + encodeURIComponent(nextdate);

                // Write POST data
                OutputStream os = connection.getOutputStream();
                os.write(postData.getBytes("UTF-8"));
                os.flush();
                os.close();

                // Get response code
                int responseCode = connection.getResponseCode();
                Log.d(TAG, "POST Response Code: " + responseCode);

                // Read response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                // Log the server response
                Log.d(TAG, "Server Response: " + response.toString());

                // Parse the server response
                JSONObject jsonResponse = new JSONObject(response.toString());
                boolean success = jsonResponse.getBoolean("success");
                String message = jsonResponse.getString("message");

                if (success) {
                    Log.d(TAG, "Server Response: Success - " + message);
                } else {
                    Log.e(TAG, "Server Response: Error - " + message);
                }

            } catch (Exception e) {
                Log.e(TAG, "Error: " + e.getMessage(), e);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }).start();
    }

    private String encodeURIComponent(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (Exception e) {
            Log.e(TAG, "Encoding Error: " + e.getMessage(), e);
            return "";
        }
    }
}
