package com.adobe.round2.service;


import com.adobe.pdfservices.operation.ExecutionContext;
import com.adobe.pdfservices.operation.auth.Credentials;
import com.adobe.pdfservices.operation.exception.SdkException;
import com.adobe.pdfservices.operation.exception.ServiceApiException;
import com.adobe.pdfservices.operation.exception.ServiceUsageException;
import com.adobe.pdfservices.operation.io.FileRef;
import com.adobe.pdfservices.operation.pdfops.DocumentMergeOperation;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.DocumentMergeOptions;
import com.adobe.pdfservices.operation.pdfops.options.documentmerge.OutputFormat;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * mergeDocument() function takes a JSON object and location of template file and calls the Adobe merge document API,
 * then returns the location of the generated pdf file
 */
@Service
public class MergeDocumentToPDF {

    // Initialize the logger.
    private static final Logger LOGGER = LoggerFactory.getLogger(MergeDocumentToPDF.class);

    public String mergeDocument(JSONObject jsonObject ,String docFile) {

        try {

            // Initial setup, create credentials instance.
            Credentials credentials = Credentials.serviceAccountCredentialsBuilder()
                    .fromFile("pdfservices-api-credentials.json")
                    .build();


            // Create an ExecutionContext using credentials.
            ExecutionContext executionContext = ExecutionContext.create(credentials);

            //Create a new DocumentMergeOptions instance
            DocumentMergeOptions documentMergeOptions = new DocumentMergeOptions(jsonObject, OutputFormat.PDF);

            // Create a new DocumentMergeOperation instance with the DocumentMergeOptions instance
            DocumentMergeOperation documentMergeOperation = DocumentMergeOperation.createNew(documentMergeOptions);

            // Set the operation input document template from a source file.
            FileRef documentTemplate = FileRef.createFromLocalFile("src/main/resources/" + docFile);

            documentMergeOperation.setInput(documentTemplate);

            // Execute the operation
            FileRef result = documentMergeOperation.execute(executionContext);

            // Save the result to the specified location.
            String outputFilePath = createOutputFilePath();
            result.saveAs(outputFilePath);

            return outputFilePath;

        } catch (ServiceApiException | IOException | SdkException | ServiceUsageException ex) {
            LOGGER.error("Exception encountered while executing operation", ex);
        }

        return null;
    }

    //Generates a string containing a directory structure and file name for the output file.
    public static String createOutputFilePath(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String timeStamp = dateTimeFormatter.format(now);
        return("output/merge" + timeStamp + ".pdf");
    }

}

