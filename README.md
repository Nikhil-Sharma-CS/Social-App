
# Social-App

Hi! This is a Project for Social App. This application gives a vague idea about how a social app works. User can create their profiles, update them, post content and get email notifications.

This is made in SpringBoot with Java language, and maven as a built type.




## 🚀 About Me
*Hi, My name is Nikhil Sharma*,

I'm a full stack developer apprentice at Geekster. I have knowledge of Java, OOPs, Maven, APIs, DSA, SpringBoot.


# Data Flow

## Controller Section
-   *This section contains a User Controller and a Auth Controller* :

* ### User Controller
    - This class contains various API endpoints that helps user to interact with the application resources. Some of the examples are :

    * @PostMapping("user/create")
    * @PutMapping("user/update-profile")
    * @PostMapping("user/create-post")
    * @GetMapping("user/see-post/{UserId}")
    * @PostMapping("user/comment-on-post")

* ### Auth Controller
    - This section is used to authenticate user and provide him a **JWT token** via Email. This helps in securing our application so that only validated users are allowed to access it.

## Service Section

- *This section contains various classes each of which contains methods for buisness logics of the APIs*

* ### User Service Class

    * This class implements the logic of how user date is processed with application, like save, update and delete.

* ### Post Service Class

    * This class implements various logics which are used to help user to post their content and manage them.

* ### Email Service Class

    * This class helps application to send email notification to the user on their email ID.

## Model Section

- *This section contains various models and a DTO which are used as tables to store data in our database.*
- ***User** and **Post** are some examples.*

## Repository Section

- *This section contains various Repositories each corresponding to our models.*

- *They all extend **JPA Repository**.*

## Security Section

- This section helps us implement and configure Spring Security according to our needs. This helps in securing our app and provided authentication service to the application. Some examples of the classes implemented in this section are :

* #### JwtAuthenticationEntry Point
* #### JwtAuthenticationFilter
* #### JwtHelper

## Database Design
- *Here we have used Relational Data to store data.*

- *We have used MYSQL and the database is hosted on a local server.*

## Summary

*This project demonstrates how a social application may work and how users can use it for their entertainment.*

*This implements various features of SprintBoot like ***Spring Actuator**(For application health checks), **Spring Security**(Securing application), **JWT**(A type of token for Spring Services), etc.*
## 🔗 Links
[![Github](https://img.shields.io/badge/Github-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://github.com/Nikhil-Sharma-CS)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/nikhil-sharma-cse)


