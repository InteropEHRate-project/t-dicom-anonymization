package eu.interopehrate.dicom_anonymization_library;

import org.apache.http.HttpResponse;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static org.toilelibre.libe.curl.Curl.curl;

public class DICOMAnonymization implements DICOMAnonymizationI {

    private static final HashMap<String, String> baseUrl = new HashMap<>() {{
        put("CHU", "http://139.165.99.12:8000/uploadfile");
        put("FTGM", "http://10.97.32.223:9000/uploadfile");
        put("LOCALHOST", "http://localhost:8000/uploadfile");
    }};

    private String declareInput(String filename) {
        String input = "";
        if(filename.contains("\\")) {
            input = filename.substring(filename.lastIndexOf("\\") + 1);
        } else if (filename.contains("/")) {
            input = filename.substring(filename.lastIndexOf("/") + 1);
        }
        return input;
    }

    private String declareFilepath(String filename) {
        String filepath = "";
        if(filename.contains("\\")) {
            filepath = filename.substring(0, filename.lastIndexOf("\\") + 1);
        } else if (filename.contains("/")) {
            filepath = filename.substring(0, filename.lastIndexOf("/") + 1);
        }
        return filepath;
    }

    private String declareOutput(String input) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
        Date date = new Date();
        String dateTime = simpleDateFormat.format(date);

        String fileExtension = "";
        if (input.indexOf(".") > 0) {
            fileExtension = input.substring(input.lastIndexOf("."));
            input = input.substring(0, input.lastIndexOf("."));
        }

        String output = "anonymized_" + input + "_" + dateTime + fileExtension;

        return output;
    }

    private void deleteFile(String filename) {
        File file = new File(filename);
        boolean delete = file.delete();
        /*if(delete) {
            System.out.println("File '" + filename + "' was successfully deleted.");
        }*/
    }

    private boolean healthcareOrganizationExists(String healthcareOrganization) {
        if(baseUrl.containsKey(healthcareOrganization)) {
            return true;
        } else {
            return false;
        }
    }

    public String invokeEndpoint(String filename, String healthcareOrganization) {

        String input = declareInput(filename);
        String filepath = declareFilepath(filename);
        String output = declareOutput(input);

        JSONObject responseMessage = new JSONObject();
        String message = "";

        if(healthcareOrganizationExists(healthcareOrganization)) {
            String url = baseUrl.get(healthcareOrganization);
            String command = "curl -X POST '" + url + "' -F 'dicom=@" + filepath + input + "' -o " + filepath + output;
            //System.out.println("Command: " + command);

            HttpResponse response = curl(command);
            int status = response.getStatusLine().getStatusCode();

            responseMessage.put("status", status);

            if (status == 200) {
                message = "File '" + input + "' was successfully anonymized.";
            } else if (status == 400) {
                message = "Anonymization process was not applied to this file. File '" + input + "' is NOT in DICOM format.";
                deleteFile(filepath + output);
            } else {
                message = response.getStatusLine().getReasonPhrase();
            }
        } else {
            message = "The Healthcare Organization provided does not exist. Please choose one in range: " + baseUrl.keySet().toString();
        }

        responseMessage.put("message", message);

        return responseMessage.toString();
    }
}
