package com.pbcr;

import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
public class YourClassName {

    // Member variables to hold the institution details
    private String institutiondtls_name;
    private String institutiondtls_dept;
    private String institutiondtls_district;
    private String institutiondtls_regdate;
    private String institutiondtls_hospitalregno;
    private String institutiondtls_firstdiagnosis_date;

    // Setter for institution details
    public void setInstitutionDetails(String name, String dept, String district, String regDate, String hospitalRegNo, String diagnosisDate) {
        this.institutiondtls_name = name;
        this.institutiondtls_dept = dept;
        this.institutiondtls_district = district;
        this.institutiondtls_regdate = regDate;
        this.institutiondtls_hospitalregno = hospitalRegNo;
        this.institutiondtls_firstdiagnosis_date = diagnosisDate;
    }

    // Method to submit data to the server
    public void submitData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                OutputStream os = null;
                BufferedWriter writer = null;
                InputStream is = null;
                BufferedReader reader = null;

                try {
                    URL url = new URL("http://10.0.2.2/insert.php");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                    os = conn.getOutputStream();
                    writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                    // Construct post data
                    String postData = URLEncoder.encode("institutiondtls_name", "UTF-8") + "=" + URLEncoder.encode(institutiondtls_name, "UTF-8") + "&"
                            + URLEncoder.encode("institutiondtls_dept", "UTF-8") + "=" + URLEncoder.encode(institutiondtls_dept, "UTF-8") + "&"
                            + URLEncoder.encode("institutiondtls_district", "UTF-8") + "=" + URLEncoder.encode(institutiondtls_district, "UTF-8") + "&"
                            + URLEncoder.encode("institutiondtls_regdate", "UTF-8") + "=" + URLEncoder.encode(institutiondtls_regdate, "UTF-8") + "&"
                            + URLEncoder.encode("institutiondtls_hospitalregno", "UTF-8") + "=" + URLEncoder.encode(institutiondtls_hospitalregno, "UTF-8") + "&"
                            + URLEncoder.encode("institutiondtls_firstdiagnosis_date", "UTF-8") + "=" + URLEncoder.encode(institutiondtls_firstdiagnosis_date, "UTF-8");

                    // Log post data
                    Log.d("SubmitData", "Post Data: " + postData);

                    // Write post data
                    writer.write(postData);
                    writer.flush();

                    // Check server response
                    is = conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Log response
                    Log.d("Response", result.toString());

                } catch (Exception e) {
                    // Detailed error logging
                    Log.e("SubmitDataError", "Error during data submission: ", e);
                } finally {
                    // Close resources
                    try {
                        if (writer != null) writer.close();
                        if (os != null) os.close();
                        if (reader != null) reader.close();
                        if (is != null) is.close();
                    } catch (IOException ex) {
                        Log.e("SubmitDataError", "Error closing streams: ", ex);
                    }
                    if (conn != null) conn.disconnect();
                }
            }
        }).start();
    }

}
