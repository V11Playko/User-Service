<br />
<div align="center">
<h3 align="center">USER SERVICE</h3>
  <p align="center">
This is a pragma challenge. In this repository will be all the logic of the user service.  </p>
</div>

### Built With

* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
* ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
* ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
* ![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)


<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these steps.

### Prerequisites

* JDK 17 [https://jdk.java.net/java-se-ri/17](https://jdk.java.net/java-se-ri/17)
* Gradle [https://gradle.org/install/](https://gradle.org/install/)
* MySQL [https://dev.mysql.com/downloads/installer/](https://dev.mysql.com/downloads/installer/)

### Prevent Possible Mistakes

* Make sure that the project structure uses SDK 17

![Project structure](docs/images/project-structure.png)

* Now go to File/Settings and search for Gradle JVM, you have to choose 17 and click apply.
![Gradle JVM](docs/images/Gradle-JVM.png)



### Recommended Tools
* IntelliJ Community [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)
* Postman [https://www.postman.com/downloads/](https://www.postman.com/downloads/)

### Installation

1. Clone the repository
2. Change directory
   ```sh
   cd User-Service
   ```
3. Create a new database in MySQL called powerup
4. Update the database connection settings
   ```yml
   # src/main/resources/application-dev.yml
   spring:
      datasource:
          url: jdbc:mysql://localhost/user-service
          username: root
          password: <your-password>
   ```
5. After the tables are created execute src/main/resources/data.sql content to populate the database
6. Open Swagger UI and search the /auth/login endpoint and login with userDni: 123, password: 1234
7. You can put the token you receive as Authorization "Bearer Token"

<!-- USAGE -->
## Usage

1. Right-click the class UserServiceApplication and choose Run
2. Open [http://localhost:8091/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html) in your web browser
3. To use postman, the entire collection I decided to save [here](docs/postman/User-Service.postman_collection.json).

<!-- ROADMAP -->
## Tests

- Right-click the test folder and choose Run tests with coverage

## Feedback
If you have any comments about the repository, please tell me so I can improve :)

- 📫 How to reach me **heinnervega20@gmail.com**
