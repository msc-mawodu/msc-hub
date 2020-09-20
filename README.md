# msc-hub
Hub application for gathering and storing data from bioinformatic pipelines. 

# Requirements
1) Maven 3.5.4
2) Java 1.8.0_26
3) Docker 

# Startup
1) Pull the repository
2) Start-up MySQL server
3) Make sure the database configuration is correctly set in the application 
4) In the terminal navigate to the project root folder and run command: 
> mvn spring-boot:run 

5) By default the application should be running on localhost port 8080, so unless it's occupied the application should be available at
> 127.0.0.1:8080