package com.pbcr;

import static android.content.ContentValues.TAG;

import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class sendPatintData {
    public void sendPatientData(String patientName, String patientIdType, String patientIdNo, String patientAge, String patientSex, String patientFather, String patientMother, String patientSpouse, String patientChild, String patientDaughter, String patientMaritalStatus, String patientMotherTongue, String patientReligion, String patientEducation) {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("http://10.0.2.2/insert2.php"); // Replace with your PHP script URL
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // Prepare POST data
                String postData = "patient_name=" + encodeURIComponent(patientName) +
                        "&patient_idtype=" + encodeURIComponent(patientIdType) +
                        "&patient_idno=" + encodeURIComponent(patientIdNo) +
                        "&patient_age=" + encodeURIComponent(patientAge) +
                        "&patient_sex=" + encodeURIComponent(patientSex) +
                        "&patient_father=" + encodeURIComponent(patientFather) +
                        "&patient_mother=" + encodeURIComponent(patientMother) +
                        "&patient_spouse=" + encodeURIComponent(patientSpouse) +
                        "&patient_child=" + encodeURIComponent(patientChild) +
                        "&patient_daughter=" + encodeURIComponent(patientDaughter) +
                        "&patient_maritalstatus=" + encodeURIComponent(patientMaritalStatus) +
                        "&patient_mothertongue=" + encodeURIComponent(patientMotherTongue) +
                        "&patient_religion=" + encodeURIComponent(patientReligion) +
                        "&patient_education=" + encodeURIComponent(patientEducation);

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
                e.printStackTrace();
                Log.e(TAG, "Error: " + e.getMessage());
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
            e.printStackTrace();
            return "";
        }
    }
}
