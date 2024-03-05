<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]


<!-- PROJECT LOGO -->
<br />
<div align="center">

<h3 align="center">Ticket Booking Service </h3>

  <p align="center">
    A project to simulate real life scenario, where you would like to book tickets for a certain event.
    <br />
    <a href="https://github.com/pjung86/otp-microservices-pjung"><strong>Explore the docs :arrow_right:</strong></a>
    <br />
    <br />
    ·
    <a href="https://github.com/pjung86/otp-microservices-pjung/issues">Report Bug :lady_beetle:</a>
    ·
    <a href="https://github.com/pjung86/otp-microservices-pjung/issues">Request Feature :memo:</a>
  </p>
</div>
   <br />
   <br />

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contributing">Contributing</a>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This backend project is about booking a ticket for a certain event. You can check what are the ongoing events and if there is a free seat you can reserve and pay for your ticket.
The microservice design pattern is followed. To comply with microservices architectire Eureka discovery server and Spring Coud Gateway were also implemented.

<p align="right">(<a href="#readme-top">:top:</a>)</p>


### Built With

* [![Java][Java.img]][Java-url]
* [![Spring][Spring.img]][Spring-url]
* [![SpringBoot][SpringBoot.img]][SpringBoot-url]
* [![Postgres][Postgres.img]][Postgres-url]

<p align="right">(<a href="#readme-top">:top:</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

Please see the core dependencies and installation steps below:

### Prerequisites
1. JAVA 17
2. MAVEN
3. POSTGRES SQL
4. IDE - for example INTELLIJ IDEA

### Installation
1. Clone the repo
   ```sh
   git clone git@github.com:pjung86/otp-microservices-pjung.git
   ```
#### In PostgreSQL
2. Create a database in PostgreSQL for every service named :
      ```sh
   partnerservice
   ```
      ```sh
   ticketservice
   ```
      ```sh
   coreservice
   ```
#### In IDE
3. Open the project in IDE from the pom.xml file :open_file_folder:
4. in otp-microservices/core-service/src/main/resources/application.properties file set the database name, username and password as environment variables as follows: <br>
   :white_check_mark: DATABASE_NAME=_coreservice_<br>
   :white_check_mark: DATABASE_USERNAME=_your username_<br>
   :white_check_mark: DATABASE_PASSWORD=_your password_<br>
5. in otp-microservices/partner-service/src/main/resources/application.properties file set the database name, username and password as environment variables as follows: <br>
   :white_check_mark: DATABASE_NAME=_partnerservice_<br>
   :white_check_mark: DATABASE_USERNAME=_your username_<br>
   :white_check_mark: DATABASE_PASSWORD=_your password_<br>
6. in otp-microservices/ticket-service/src/main/resources/application.properties file set the database name, username and password as environment variables as follows: <br>
   :white_check_mark: DATABASE_NAME=_ticketservice_<br>
   :white_check_mark: DATABASE_USERNAME=_your username_<br>
   :white_check_mark: DATABASE_PASSWORD=_your password_<br>
   
##### Backend Side
7. Run the application by the "Run" button in the top right corner or with Shift + F10 shortcut in INTELLIJ IDEA. You need to start the individual services by selecting the "Application" files from the drop down menu next to the Run button. 
8. This will start each service one by one.
9. Or you can click on the "Services" tab on the bottom of IntelliJ, then select "Spring Boot" on the left side of IntelliJ and click the Run button at the left edge of the screen. This will start all
services at once.

    
<p align="right">(<a href="#readme-top">:top:</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage
### Patner-service
  In this service you have 3 end points. Two GET and one Post endpoints.
  With the getEvents/ end point you can ask for all the events in the DB.
  With the getEvent/{id} end point you can ask for one event with a specific id.
  With the /reserve end point you can place a reservation.

### Ticket-service
  This service is responsible for communicationg with the Partner-service. They communicate with http rest calls.
  Ticket service ask for all the events or only one event by id.
  Ticket service also communicates with the Core-service. It can request for user data and bank card data.
  After that it validates all the incoming information and you can initiate a payment call through a POST method.
  IF everything successful ticket reservation and payment is fullfilled.

### Core-service
  Core-service manages user details and user bank card details. It can validated if the owner of the card is the specific user by id.
  It also validates the credit of the card.
  It sends the necessary information to the ticket-service

### Discovery server
  This service is responsible of registering the instances of the other services.

### Api gateway
  The gateway is responsible for managing incoming client requests and forwards it to the respective service.
  Gateway filter is used for authenticating requests by checking the User-Token in the header.



<p align="right">(<a href="#readme-top">:top:</a>)</p>

## Future Plans

  Unfortunately do to the tight sprint not all the requirements were fullfilled.
  In the next sprint the following should be impletmented:
  - debug GatewayFilter that has been implemented:
    right now the filters are implemented, but not working thus commented out in the api gateways application.properties file
  - creating UML diagrams of use-case, activity and sequence with Plantuml
  - by writing unit test with Junit reach the test coverage at least to 80% in order to produce a stable build
  - writing integrastion test to avoid regression (testcontainers)
  - setting up the logging mechanism with logger or log4j (both console and log file)

<p align="right">(<a href="#readme-top">:top:</a>)</p>

<!-- CONTRIBUTING -->
## Contributing
This project was made by four Codecool student: Péter Jung

<!-- CONTACT -->
## Contact
:man_technologist: Péter Jung - :email: jung.peter24[at]gmail[dot]com [![LinkedIn][linkedin-shield]][linkedin-Peter]<br>

Project Link: [https://github.com/pjung86/otp-microservices-pjung](https://github.com/pjung86/otp-microservices-pjung)

<p align="right">(<a href="#readme-top">:top:</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/pjung86/otp-microservices-pjung?style=for-the-badge
[contributors-url]: https://github.com/pjung86/otp-microservices-pjung/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/pjung86/otp-microservices-pjung?style=for-the-badge
[forks-url]: https://github.com/pjung86/otp-microservices-pjung/forks
[stars-shield]: https://img.shields.io/github/stars/pjung86/otp-microservices-pjung?style=for-the-badge
[stars-url]: https://github.com/pjung86/otp-microservices-pjung/stargazers
[issues-shield]: https://img.shields.io/github/issues/pjung86/otp-microservices-pjung?style=for-the-badge
[issues-url]: https://github.com/pjung86/otp-microservices-pjung/issues

[linkedin-shield]: https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white
[linkedin-Peter]: https://www.linkedin.com/in/pjung-dev
[Java.img]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.java.com/en/
[Spring.img]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/
[Postgres.img]: https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white
[Postgres-url]: https://www.postgresql.org/
[SpringBoot.img]: https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot
[SpringBoot-url]: https://spring.io/projects/spring-boot
