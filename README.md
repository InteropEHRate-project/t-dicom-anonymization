# Terminal DICOM Structured Content Anonymization Library

## Description

The DICOM Structured Content Anonymization Library has been developed in Java programming language and aims at invoking the endpoint `/uploadfile` of DICOM Structured Content Anonymization Service. The response of the library is essentially the response of the service which is the previously uploaded DICOM image with anonymized structured content.

## Installation Guide

In order to integrate the DICOM Structured Content Anonymization Library there are some steps which should be followed.

1.	In case of a Gradle project, the following line should be appended in the dependencies section of the **build.gradle** file:
```
implementation(group:'eu.interopehrate', name:'dicom_anonymization_library', version: '0.0.3')
```

2. In case of a Maven project, the same dependency should be expressed with the following Maven syntax:
 ```
<dependency>
    <groupId>eu.interopehrate</groupId>
    <artifactId>dicom_anonymization_library</artifactId>
    <version>0.0.3</version>
</dependency>
 ```

## Methods

### **request**

<ins>Parameters:</ins>
  * the _**base64Str**_, which is the encoded Base64 form of the DICOM image that will get anonymized through the DICOM Structured Content Anonymization Service.

<ins>Response:</ins> If the anonymization operation has been successfully applied to the DICOM image, the response will be as follows and the anonymized DICOM image will be stored within the **data** key-value pair.

```
{
    "status":"200",
    "message":"DICOM was successfully anonymized!",
    "data":"$data"
}
```

<ins>Error response:</ins> If the uploaded file is not in DICOM format the response will be as follows.

```
{
    "status":"400",
    "message":"Anonymization process was not applied to this file since it is NOT in DICOM format."
}
```
