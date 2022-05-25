package eu.interopehrate.dicom_anonymization_library;

public interface DICOMAnonymizationI {
    String invokeEndpoint(String filename, String healthcareOrganization);
}
