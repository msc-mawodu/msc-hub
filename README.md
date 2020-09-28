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
4) Make sure "files.storage.path" is set in the application.properties file. It should be pointing to an existing folder to which files will be downloaded and uploaded from.
5) In the terminal navigate to the project root folder and run command: 
> mvn spring-boot:run 

5) By default the application should be running on localhost port 8080, so unless it's occupied the application should be available at
> 127.0.0.1:8080

# Integrating with Docker based MySQL instance
1) Download and install Docker. 
2) Check if docker is running
<pre> docker ps </pre>  
3) Pull latest mysql image from Docker 
<pre> >> docker pull mysql/mysql-server:latest </pre> 
4) Run the docker container 
<pre> docker run --detach --name=hub-mysql --publish 6603:3306 -d mysql/mysql-server:latest </pre> 
5) Check if the container is running
<pre> >> docker inspect hub-mysql </pre>
+ Make note of the IPAddress and ExposedPorts fields, as they will be used later for in the application.properties 
6) Check the MySQL logs 
<pre> docker logs hub-mysql </pre>
+ Make note of the GENERATED ROOT PASSWORD field, this will also be used later to configure the application.properties (e.g. gusnUNl4dris3bYJIRElj3cUc@l)
7) Create table by connecting and logging into the MySQL database running now in Docker (use the password obtained from step 6).  
<pre> >> docker exec -it hub-mysql mysql -uroot -p </pre>
8) Once in the mysql console, change generated root password (in this example password is set to 'pwd' for simplicity) 
<pre> mysql>> ALTER USER 'root'@'localhost' IDENTIFIED BY 'pwd'; </pre> 
9) Create the database schema to be used inside the hub application:
<pre> mysql>> create database hub; </pre>
10) Create user that to be used by the web application to perform actions on the database (in this example password is set to 'pwd' for simplicity):
<pre> mysql>> create user hubapp identified by 'pwd'; </pre>
11) Grant the newly created application-user full access to all schema in db
<pre> mysql>> grant all on hub.* to 'hubapp'; </pre>
12) Exit mysql console
<pre> mysql>> exit </pre>
13) Configure application.properties with following data: 
<pre>
spring.datasource.url=jdbc:mysql://172.17.0.2:3306/hub
spring.datasource.username=hubapp
spring.datasource.password=pwd
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
</pre>
14) NB. Alternatively the database can be run as a service on a host machine, in such case mysql client needs to be installed first. The database configuration steps are similiar to those outlined for docker. 

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
 
 - Manual Files Download and Upload.
 
 Assuming the GATK pipeline was created (as outlined in previous steps) it should be possible to navigate to following url: 
 <pre>
   localhost:8080/pipeline/GATK
 </pre>
 
 From the pipeline view:
  1) By clicking "Chose File" should open a window prompt to select file from a filesystem.
  Select a file and confirm selection. 
  2) By Clicking "Upload" button the page will refresh, and the uploaded file should be visible in the Files section. 
  3) By clicking any file from the files section a file download should be automatically started.
  4) By returning to home page, for the pipeline for which the file was uploaded counter should reflect that as well.   
 
 - Pipeline Notes Editing. 
 
   Assuming the GATK pipeline was created (as outlined in previous steps) it should be possible to navigate to following url: 
  <pre>
    localhost:8080/pipeline/GATK
  </pre>
  
  From the pipeline view:
    1) By clicking on the Notes section it should be possible to start editing content of the notes.
    2) By Clicking "Save Notes" buttonthe uploaded notes should be visible.
    3) By Refreshing page or exiting and entering this view it can be verified if the notes were persisted.