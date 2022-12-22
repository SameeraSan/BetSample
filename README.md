### START

## Bet Service
---
### Introduction

Test assignment:

Create backend for a game:

* Player is sending a bet and whole number from 1 to 100 to a server
* Server generates random whole number from 1 to 100 and if players number is greater, calculates win and sends it back to client.
* Win depends on chance - = bet * (99 / (100 - number)), as an example if player selected number 50 and bet 40.5, win would be 80,19

Requirements:

* Java 11
* Spring boot 2
* REST + JSON
* Unit and Integration tests
* Data validation

Additional task:

To write a test that is going to play 1 million rounds in parallel in 24 threads and will calculate how much money player is recieving back (RTP)
Example: For 1 million games player has spent 1 million and had won 990000, RTP is going to be 99%
---

# Project Instructions

### Requirements

- Java 11
- IDE (IntelliJ)
- Container environment (Docker)
- Test tool (Postman)
---

#### How to build

- Clone the Main branch from  GIT - https://github.com/SameeraSan/BetSample.git
- Import the project to intelliJ
- Configure the application.properties (refer application.property Configure section)
- Update Maven
- Maven build

### application.property Configure

- server.port=7761

### Once you complete the configuring project you can run the project 
> To run the project you can use either the IDE or the provided docker image, below you can find the docker run instructions
> 
#### Docker instructions

- download the docker image from the provided url 
- docker load -i BetDockerImage.tar\
- docker run -p **port**:7761 **imageID/name**
> Give a port to **port**, and give either the imageId or the name to **imageID/name**

--- 

### Assumptions
- Did not implement any security mechanism as there was no such requirement.
- Dit not try to cover all test scenarios in unit tests, and added few just to demonstrate the skill


---

### How Test the services

- Requests MediaType: **APPLICATION_JSON**
- Responses MediaType: **APPLICATION_JSON**

#### Place a bet
- Place bet register url (get) = **http://localhost:7761/v1/bet**
> Request : {
"number":500,
"bet" :100
}
> 
> Response : {
"win": true,
"winningAmount": 185.90"
}
> 

---

### END