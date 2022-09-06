package dummy;

import eu.interopehrate.dicom_anonymization_library.DICOMAnonymization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        String base64Str = "";

        DICOMAnonymization dicomAnon = new DICOMAnonymization("http://localhost:8000");
        String response = dicomAnon.request(base64Str);
        System.out.println("Response: " + response);
    }
}