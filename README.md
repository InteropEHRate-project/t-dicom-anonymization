# Terminal DICOM Structured Content Anonymization Library

## Description

The DICOM Structured Content Anonymization Library has been developed in Java programming language and aims at invoking the endpoint `/uploadfile` of DICOM Structured Content Anonymization Service. The response of the library is essentially the response of the service which is the previously uploaded DICOM image with anonymized structured content.

## Methods

### **invokeEndpoint**

<ins>Parameters:</ins>
  * the _**filename**_, which is the absolute path of the DICOM image that will get anonymized through the DICOM Structured Content Anonymization Service.

<ins>Response:</ins> If the anonymization operation has been successfully applied to the DICOM image, the response will be as follows and the anonymized DICOM image will be downloaded automatically to the same path as the original one without deleting it.

```
{
    "status": 200
    "message": "File '$filename$' was successfully anonymized."
}

```

<ins>Error response:</ins> If the uploaded file is not in DICOM format the response will be as follows.

```
{
    "status": 400
    "message": " Anonymization process was not applied to this file. File '$filename$' is NOT in DICOM format."
}
```
