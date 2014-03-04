## Spring Security Lab
This project is created to try out different features provided by the spring security. 

I have the plan to do the following thing

* One RESTful entpoint to describe a service
* UserDetail Service for Authentication and Authorization
* Appling security to the service

## Prerequisites
You need the following packages to be installed:
* Java 7
* Tomcat 7
* Maven 3.0.5
* Git

## Building from source
After completing the prerequisites, you can follow the instructions to build the project.

### check out sources

	git clone https://github.com/karasatishkumar/spring-security-lab.git

once you clone the project change the tomcat webapp path in your parent pom.xml. So that your wars will be get copied to webapp.	

### compile and test, build all wars

	mvn clean install

One successfull built the project, you can start tomcat to test..

## Contributing
Pull requests are welcome.

## Staying in touch

## License

## Release
* One servie has been created called the account.
* ControllerAdvice has been implemented to handle application exceptions and hook that up with HTTP status codes.
