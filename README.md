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
 