package dummy;

import eu.interopehrate.dicom_anonymization_library.DICOMAnonymization;

public class Main {
    public static void main(String[] args) {

        //String filename = "C:\\Users\\stell\\Desktop\\DICOM\\I0";
        String filename = "C:/Users/stell/Desktop/DICOM/I0";

        DICOMAnonymization dicomAnon = new DICOMAnonymization("http://localhost:8000");
        String response = dicomAnon.invokeEndpoint(filename);
        System.out.println("Response: " + response);
    }
}