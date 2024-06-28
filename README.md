# Auction Website Project

## Purpose:
This project aims to build an online auction platform where users can buy and sell products through online auction sessions.

## Key Features:
- User registration and profile management.
- Creating and managing auction sessions.
- Real-time bidding and auction status tracking.
- Payment processing and transaction management (under construction).

## Technologies Used:
- Frontend: JavaScript
- Backend: Java, Spring Boot
- Database: MS-SQL Server
- Build Tools: Maven
- Development Tools: Intellij IDEA, Visual Studio Code, Git

## Code Structure:
```
src
   |_ main
       |_ java
            |_ com/example/auctionwebsite
                |_ controller
                    |_ AuctionController.java
                    |_ BiddingController.java
                    |_ EditProfileController.java
                    |_ ItemController.java
                    |_ LoginController.java
                    |_ ProfileController.java
                    |_ RegisterController.java
                    |_ RoomController.java
                |_ model
                    |_ AuctionRoom.java
                    |_ EditProfileInfo.java
                    |_ ItemInfo.java
                    |_ LoginInfo.java
                    |_ RoomInfo.java
                    |_ UserInfo.java
                |_ DatabaseConfig.java
                |_ AuctiononlineApplication.java
            |_ reposistory
            
       |_ resources
           |_ static
               |_ Asset
               |_ Bidding room
                    |_ Bidding room.css
                    |_ Bidding room.js
               |_ Bidding
                    |_ Bidding.css
                    |_ Bidding.js
               |_ Homepage
                    |_ Homepage.css
                    |_ Homepage.js
               |_ Login
                    |_ Login.css
                    |_ Login.js
               |_ Profile
                    |_ Profile.css
                    |_ Profile.js
               |_ Signup
                    |_ Signup.css
                    |_ Signup.js
               |_ Bidding room.html
               |_ Bidding.html
               |_ Homepage.html
               |_ Login.html
               |_ Profile.html
               |_ Signup.html
```

### How to run ?
- Clone this repository
- Run file `pom.xml`
- Run `docker-compose up`
- Run class `AuctiononlineApplication` in `./demo/src/main/java/com/example/demo` to run the backend
- Open localhost `http://localhost/-port-` to open the website

### Authors:
- Nguyen Khoi Nguyen
- Bui Xuan Son

### License:
This project is licensed under the MIT License - see the LICENSE.md file for details.

