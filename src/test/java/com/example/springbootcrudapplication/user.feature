Feature: User

    Background:
        Given url "http://localhost:7000/users"

    Scenario: Index user
        Given path "/1"
        When method get
        Then status 200
        And match response == { id: 1, firstName: "Bert", name: "Ernie", email: "bert@example.com" }

    Scenario: Index and filter by first name
        Given param filter = "firstName:Schneewittchen"
        Given path "/"
        When method get
        Then status 200
        And match response[*].id contains [3, 4]

    Scenario: Show user
        Given path "/1"
        When method get
        Then status 200
        And match response == { id: 1, firstName: "Bert", name: "Ernie", email: "bert@example.com" }

    Scenario: Show on none existing user
        Given path "/50"
        When method get
        Then status 404
        And match response.message == "User not found with id 50"

    Scenario: Create user
        Given path "/"
        When request { firstName: "Karte", name: "test", email: "karte@test.eu" }
        And method post
        Then status 200
        And match response == { id: "#notnull", firstName: "Karte", name: "test", email: "karte@test.eu" }
        * def user_id = response.id

        Given path "/" + user_id
        When method get
        Then status 200
        And match response == { id: "#notnull", firstName: "Karte", name: "test", email: "karte@test.eu" }

    Scenario Outline: Update attribute <attribute>
        Given path "/3"
        When request { <attribute>: <value> }
        And method patch

        Examples:
            | attribute | value                 |
            | firstName | Ernie                 |
            | name      | Bert                  |
            | email     | erni.bert@example.org |

    Scenario: Call update on none existing user
        Given path "/50"
        When request { firstName: "Karte", name: "test", email: "karte@test.eu" }
        And method patch
        Then status 404
        And match response.message == "User not found with id 50"

    Scenario: Call delete user
        Given path "/1"
        When method delete
        Then status 204

    Scenario: Call delete on none existing user
        Given path "/50"
        When method delete
        And match response.message == "User not found with id 50"


