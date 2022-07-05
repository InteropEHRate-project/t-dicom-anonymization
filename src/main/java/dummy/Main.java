package dummy;

import eu.interopehrate.dicom_anonymization_library.DICOMAnonymization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String base64Str = "";

        /*String path = "C:\\Users\\user\\Downloads\\Encoded_Base64_DICOM.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String currentLine = "";
        StringBuilder stringBuilder = new StringBuilder();
        while((currentLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(currentLine.trim());
        }
        bufferedReader.close();
        base64Str = stringBuilder.toString();
        System.out.println("Encoded Base64 String: " + base64Str);*/

        DICOMAnonymization dicomAnon = new DICOMAnonymization("http://localhost:8000");
        String response = dicomAnon.request(base64Str);
        System.out.println("Response: " + response);
    }
}