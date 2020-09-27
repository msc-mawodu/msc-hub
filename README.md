# msc-hub
Hub application for gathering and storing data from bioinformatic pipelines. 

# Requirements
- Maven 3.5.4
- Java 1.8.0_26
- Docker 
- MySQL

# Startup
1) Pull the repository
2) Start-up MySQL server
3) Make sure the database configuration is correctly set in the application
4) Make sure "files.storage.path" is correctly set in the application.properties file. It should be pointing to an existing folder to which files will be downloaded/uploaded.
5) In the terminal navigate to the project root folder and run command: 
> mvn spring-boot:run 

5) By default the application should be running on localhost port 8080, so unless it's occupied the application should be available at
> 127.0.0.1:8080

# Development
- CSS: project uses sass/scss files to defile css stylesheets. Maven is responsible for compilation from sccs to css.
- To compile scss to css run following command (NB. this can be done with the main application running).
> mvn sass:watch
 
 
 # Testing
 
 Using Postman or similar tool to perform HTTP requests evaluate all endpoints.
 All tests assume application is running locally and on port 8080.  
 
 - Status update endpoint
 
 Using Postman, make POST request with JSON body to the following endpoint:
 <pre>
 localhost:8080/status
 </pre>
 
 <pre>
 Set the following request headers: 
    
 Accept : application/json,
 Content-Type : application/json
 </pre> 
 
 <pre>
 JSON body example:
 
 {"id":"GATK","description":"A genomic analysis toolkit focused on variant discovery. The GATK is the industry standard for identifying SNPs and indels in germline DNA and RNAseq data.", "ip":"192.182.0.1", "state":"idle"}
 </pre>
 
 Expected outcome: 
 1) In the application log there should be a confirmation message.
 2) By navigating to homepage the "GATK" pipeline should be now listed as being IDLE, and have a matching description.
 
 
 - File Upload
 
 Using postman, make POST request with attached file (e.g. as form data) to the following endpoint: localhost:8080/upload/GATK
 NB. The file has to be contained in "file" field, there is no particular format requirement for it. This also assumes the GATK pipeline is registered within the system.
 <pre>
  localhost:8080/upload/GATK
 </pre>  

Expected outcome: 
 1) In the application log there should be a confirmation message.
 2) By navigating to pipeline "GATK" page the uploaded file should be listed.
 3) On the homepage the "GATK" entry will the file count column will be updated (e.g. from 0 to 1).  
 