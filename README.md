# Adobe_Hackathon_Round_2
Submission for the round 2 of Adobe PapyrusNebula Hackathon
## Resume Builder API

## Task
Create an API that uses provided resume templates and other information to create resumes. The API will pass the template and report to the Adobe Document Generation API to generate a merged document.

## How to run
### Spring Boot Project which follows RESTfull standards to create the API
### Uses thymeleaf templating to create the front end
##### Run the AdobeRound2Application.java to start the spring application 

## API Specifications
##### Location: http://localhost:8080/resume
##### URL: http://localhost:8080
##### Endpoint:  /resume POST
##### Header: Content-Type- application/json | data raw format
##### [Sample curl request](Sample%20curl%20request.txt)

## UI Specifications
##### Location: http://localhost:8080/resume/ui
##### Fill the form, click the submit button , pdf file will show up

## What this application does ?
1. Gets the Template ID and other relevant information
2. pass that information  to **Adobe Document Generation API** merge document option to generate the PDF file

## How Application works
### API
1. When POST request is made to **/resume**, server listens and calls the method of **_ResumeController class_**.
2. after certain **_validation_** checks, if fails throws the appropriate exception
3. else passed validation, calls the _getPDFile() method of MainService class_
4. calls the getJSONObject() of JSONDataService class to get the JSON object appropriate to pass to Adobe API
5. calls the mergeDocument() of MergeDocumentToPDF class to call the Adobe Document API and get the PDF file

### UI
1. When GET request is made to **/resume/ui**, server listens and calls the method of **_ResumeController class_**.
2. calls the initializeDetails() of MainService class to initialize the Details object, 
3. As we are using thymeleaf templating for dynamically feature, input tag will spawn accordingly
4. After submitting the form ,POST request is made to **/resume/ui**, server listens and calls the method of **_ResumeController class_**.
5. else passed validation, calls the _getPDFile() method of MainService class_
6. calls the getJSONObject() of JSONDataService class to get the JSON object appropriate to pass to Adobe API
7. calls the mergeDocument() of MergeDocumentToPDF class to call the Adobe Document API and get the PDF file

## Folder Structure
1. **src\main\java\com\adobe\round2\controller** : contains the controller class 
2. **src\main\java\com\adobe\round2\entity** :  contains all entity classes 
3. **src\main\java\com\adobe\round2\service** : contains all the service classes
4. **src\main\java\com\adobe\round2\exceptionhandler** : contains all the required classes to handle the exceptions
5. **src\main\resources\templates** : contains the form.html file
6. **src\main\resources\Template1/2/3** : folders which contains the template files in form of .docx

## What each classes are for
1. **ResumeController.java** : all routes are mapped to functions
2. **MainService.java** : contains the main logic behind this whole application
3. **JSONDataService.java** : generates JSON object which is compatible to be used in Adobe Document Generation API merge Document.
4. **MergeDocumentToPDF.java** : calls the Adobe merge document API
5. **Details.java** : model class which contains variables, to store the data from api and ui.