package com.pbcr;

import static android.content.ContentValues.TAG;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class CancerRelated {

    public void sendCancerRelatedData(String cancerinfopathologyno, String cancerinfodate, String cancerinfoprimarytumoursite,
                                      String cancerinfoprimaryhistology, String cancerinfosecondarytumoursite,
                                      String cancerinforemarksecsite, String cancerinfosecondaryhistology,
                                      String cancerinfohistologicgrading, String cancerinfohistologicgradingsec,
                                      String cancerinfoicd10, String cancerinfolaterality, String cancerinfosequence,
                                      String cancerinfosequencesite, String cancerinfoclinicalextent, String cancerinfotnmt,
                                      String cancerinfotnmn, String cancerinfotnmm, String cancerinfocomposite,
                                      String cancerinfohospname, String cancerinfohospregno, String cancerinfodatereg,
                                      String cancerinfotreattaken, String cancerinfointention, String cancerinfotretmentreceived,
                                      String cancerinfotretmentdate, String cancerinfoistreatcomp, String cancerinfodateofcompletion,
                                      String cancerinfodatelastcontact, String cancerinfofamilyhistory, String cancerinfoinformantname,
                                      String cancerinfoinformantrelation, String cancerinfoinformantmob) {

        new Thread(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL("http://10.0.2.2/cncrrelated.php"); // Update URL as needed
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                // Prepare POST data
                String postData = "cancerinfo_pathologyno=" + encodeURIComponent(cancerinfopathologyno) +
                        "&cancerinfo_date=" + encodeURIComponent(cancerinfodate) +
                        "&cancerinfo_primary_tumoursite=" + encodeURIComponent(cancerinfoprimarytumoursite) +
                        "&cancerinfo_primary_histology=" + encodeURIComponent(cancerinfoprimaryhistology) +
                        "&cancerinfo_secondary_tumoursite=" + encodeURIComponent(cancerinfosecondarytumoursite) +
                        "&cancerinfo_remarksecsite=" + encodeURIComponent(cancerinforemarksecsite) +
                        "&cancerinfo_secondary_histology=" + encodeURIComponent(cancerinfosecondaryhistology) +
                        "&cancerinfo_histologicgrading=" + encodeURIComponent(cancerinfohistologicgrading) +
                        "&cancerinfo_histologicgrading_sec=" + encodeURIComponent(cancerinfohistologicgradingsec) +
                        "&cancerinfo_icd10=" + encodeURIComponent(cancerinfoicd10) +
                        "&cancerinfo_laterality=" + encodeURIComponent(cancerinfolaterality) +
                        "&cancerinfo_sequence=" + encodeURIComponent(cancerinfosequence) +
                        "&cancerinfo_sequence_site=" + encodeURIComponent(cancerinfosequencesite) +
                        "&cancerinfo_clinicalextent=" + encodeURIComponent(cancerinfoclinicalextent) +
                        "&cancerinfo_tnm_t=" + encodeURIComponent(cancerinfotnmt) +
                        "&cancerinfo_tnm_n=" + encodeURIComponent(cancerinfotnmn) +
                        "&cancerinfo_tnm_m=" + encodeURIComponent(cancerinfotnmm) +
                        "&cancerinfo_composite=" + encodeURIComponent(cancerinfocomposite) +
                        "&cancerinfo_hosp_name=" + encodeURIComponent(cancerinfohospname) +
                        "&cancerinfo_hosp_regno=" + encodeURIComponent(cancerinfohospregno) +
                        "&cancerinfo_date_reg=" + encodeURIComponent(cancerinfodatereg) +
                        "&cancerinfo_treat_taken=" + encodeURIComponent(cancerinfotreattaken) +
                        "&cancerinfo_intention=" + encodeURIComponent(cancerinfointention) +
                        "&cancerinfo_tretmentreceived=" + encodeURIComponent(cancerinfotretmentreceived) +
                        "&cancerinfo_tretment_date=" + encodeURIComponent(cancerinfotretmentdate) +
                        "&cancerinfo_istreat_comp=" + encodeURIComponent(cancerinfoistreatcomp) +
                        "&cancerinfo_dateof_completion=" + encodeURIComponent(cancerinfodateofcompletion) +
                        "&cancerinfo_date_lastcontact=" + encodeURIComponent(cancerinfodatelastcontact) +
                        "&cancerinfo_family_history=" + encodeURIComponent(cancerinfofamilyhistory) +
                        "&cancerinfo_informant_name=" + encodeURIComponent(cancerinfoinformantname) +
                        "&cancerinfo_informant_relation=" + encodeURIComponent(cancerinfoinformantrelation) +
                        "&cancerinfo_informant_mob=" + encodeURIComponent(cancerinfoinformantmob);

                // Write POST data to the output stream
                OutputStream os = connection.getOutputStream();
                os.write(postData.getBytes("UTF-8"));
                os.flush();
                os.close();

                // Get response code and log
                int responseCode = connection.getResponseCode();
                Log.d(TAG, "POST Response Code: " + responseCode);

                // Read the server response
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
            Log.e(TAG, "Encoding Error", e);
            return "";
        }
    }
}
