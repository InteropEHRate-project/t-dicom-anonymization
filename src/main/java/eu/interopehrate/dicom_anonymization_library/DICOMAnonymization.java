package eu.interopehrate.dicom_anonymization_library;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class DICOMAnonymization implements DICOMAnonymizationI {

    private String baseUrl;
    private static final String endpoint = "/uploadfile";

    public DICOMAnonymization(){
        this.baseUrl = "http://localhost:8000";
    }

    public DICOMAnonymization(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String request(String base64Str) {

        StringBuilder response = new StringBuilder();
        String currentUrl = baseUrl + endpoint;

        try {
            URL url = new URL(currentUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "multipart/form-data");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            byte[] input = Base64.getDecoder().decode(base64Str.getBytes("ASCII"));
            outputStream.write(input);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "ASCII"));
            writer.close();

            connection.connect();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "ASCII"));
            String responseLine = null;
            while ((responseLine = bufferedReader.readLine()) != null) {
                response.append(responseLine.trim()); //
                //System.out.println(responseLine.trim());
            }
            //System.out.println(response.toString());
            bufferedReader.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }
}
