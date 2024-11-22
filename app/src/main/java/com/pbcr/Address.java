package com.pbcr;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Address {
    private static final String TAG = "Address";

    public void sendAddress(String houseName, String district, String panchayath, String area, String place, String postoffice, String ward, String taluk, String village, String duration, String phoneno, String phc, String subcenter, String residency) {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("http://10.0.2.2/insert2.php"); // Replace with your PHP script URL
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // Prepare POST data
                String postData = "patient_housename=" + encodeURIComponent(houseName) +
                        "&patient_district=" + encodeURIComponent(district) +
                        "&patient_localbody=" + encodeURIComponent(panchayath) +
                        "&patient_area=" + encodeURIComponent(area) +
                        "&patient_place=" + encodeURIComponent(place) +
                        "&patient_postoffice=" + encodeURIComponent(postoffice) +
                        "&patient_ward=" + encodeURIComponent(ward) +
                        "&patient_taluk=" + encodeURIComponent(taluk) +
                        "&patient_village=" + encodeURIComponent(village) +
                        "&patient_durationofstay=" + encodeURIComponent(duration) +
                        "&patient_phoneno=" + encodeURIComponent(phoneno) +
                        "&patient_phc=" + encodeURIComponent(phc) +
                        "&patient_subcenter=" + encodeURIComponent(subcenter) +
                        "&patient_residanceplace=" + encodeURIComponent(residency);

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
