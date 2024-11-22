package com.pbcr;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class sendmortality {
    private static final String TAG = "sendmortality";

    public void sendMortality(String registernumber, String doctorname, String hospitalname, String hospitaladdress, String deathDate, String deathofcause, String placeofdeath) {
        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("http://10.0.2.2/mortalityn.php"); // Change to your actual server URL
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // Prepare POST data
                String postData = "cancerinfo_mortality_regno=" + encodeURIComponent(registernumber) +
                        "&cancerinfo_certified_dr=" + encodeURIComponent(doctorname) +
                        "&cancerinfo_hospital=" + encodeURIComponent(hospitalname) +
                        "&cancerinfo_hosp_addr=" + encodeURIComponent(hospitaladdress) +
                        "&cancerinfo_death_date=" + encodeURIComponent(deathDate) +
                        "&cancerinfo_deathcause=" + encodeURIComponent(deathofcause) +
                        "&cancerinfo_deathplace=" + encodeURIComponent(placeofdeath);

                // Write POST data to server
                OutputStream os = connection.getOutputStream();
                os.write(postData.getBytes("UTF-8"));
                os.flush();
                os.close();

                // Get response code and log it
                int responseCode = connection.getResponseCode();
                Log.d(TAG, "POST Response Code: " + responseCode);

                // Read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                // Log server response
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

    // Encode URL component safely
    private String encodeURIComponent(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
