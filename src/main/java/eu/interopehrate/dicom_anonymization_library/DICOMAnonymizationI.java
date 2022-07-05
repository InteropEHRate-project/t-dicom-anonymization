package eu.interopehrate.dicom_anonymization_library;

import java.io.IOException;

public interface DICOMAnonymizationI {
    String request(String base64Str) throws IOException, InterruptedException;
}