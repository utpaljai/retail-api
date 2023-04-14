# retail-api
Retail API to track customer's reward points for purchases made by customers

Business Requirement
A retailer offers a rewards program to its customers, awarding points based on each
valid purchase. A customer receives 2 points for every dollar spent over $100 in each
transaction, plus 1 point for every dollar spent over $50 in each transaction (e.g. if one
customer spends $120 total in a transaction, then points earned is 2x$20 + 1x$50 = 90
points).
Given all the transactions of every customer during a period of time(1 year for example),
calculate the reward points earned for each customer per month and total.


Technology used: Spring boot(2.3.1), spring data JPA, In memory DB(H2)

Prerequisites: Java 8, Maven 3, Git Bash(for checking out Git source code)

# Build and run: 

From git bash terminal, clone retail-api from git.

$git clone https://github.com/utpaljai/retail-api.git

From folder where git project is cloned, chage directory:

$ cd retail-api

$ mvn spring-boot:run

This will start embedded Apache Tomcat/9.0.36 server

# Test rest end points using postman
Note: data.sql file is used(in src/main/resources) to load in-memory h2 db with customer and it's transactions data.

Get reward point summary for a given customer by customerId. Points are grouped by each month of a year in which transactions were made.

    GET http://localhost:8080/customers/1/rewards
    
    Under Headers tab add- Content-Type=application/json

    Response returned:
    
{
    "customerId": 1,
    "customerName": "Customer1",
    "pointsByMonthMap": {
        "2023-04-01": 70,
        "2023-03-01": 690,
        "2023-02-01": 452,
        "2023-01-01": 30,
        "2022-09-01": 0,
        "2022-06-01": 510
    },
    "totalPoints": 1752
}        


Get reward point summary for a given customer by customer name. If customer name is not passed then reward summary is returned for all customers.

    GET http://localhost:8080/customers/rewards?customer-name=Customer3

    Response returned

[
    {
        "customerId": 3,
        "customerName": "Customer3",
        "pointsByMonthMap": {
            "2023-02-01": 452,
            "2023-01-01": 340,
            "2022-09-01": 20,
            "2022-06-01": 50,
            "2023-04-01": 70,
            "2023-03-01": 670
        },
        "totalPoints": 1602
    },
    {
        "customerId": 4,
        "customerName": "Customer3",
        "pointsByMonthMap": {
            "2023-03-01": 50,
            "2023-01-01": 650
        },
        "totalPoints": 700
    }
]


    GET http://localhost:8080/customers/rewards
    Response returned

[
    {
        "customerId": 1,
        "customerName": "Customer1",
        "pointsByMonthMap": {
            "2023-04-01": 70,
            "2023-03-01": 690,
            "2023-02-01": 452,
            "2023-01-01": 30,
            "2022-09-01": 0,
            "2022-06-01": 510
        },
        "totalPoints": 1752
    },
    {
        "customerId": 2,
        "customerName": "Customer2",
        "pointsByMonthMap": {
            "2022-04-01": 160,
            "2023-03-01": 840,
            "2023-02-01": 454,
            "2023-01-01": 40,
            "2022-09-01": 0,
            "2022-06-01": 190
        },
        "totalPoints": 1684
    },
    {
        "customerId": 3,
        "customerName": "Customer3",
        "pointsByMonthMap": {
            "2023-02-01": 452,
            "2023-01-01": 340,
            "2022-09-01": 20,
            "2022-06-01": 50,
            "2023-04-01": 70,
            "2023-03-01": 670
        },
        "totalPoints": 1602
    },
    {
        "customerId": 4,
        "customerName": "Customer3",
        "pointsByMonthMap": {
            "2023-03-01": 50,
            "2023-01-01": 650
        },
        "totalPoints": 700
    }
]
    
