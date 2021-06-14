Feature: Verify Users

  Background:
    Given url 'http://localhost:8082/'

  Scenario: 01 Get all Users
    Given path 'users'
    When method get
    Then status 200
    Then match response == '#array'
	
  Scenario: 02 Create a New User
    Given path 'users'
	* def user_request = { "firstName": "nombreKarate","lastName": "apellidoKarate"}
    And request user_request
    When method POST
    Then status 201
    * def userId = response.userId

    Given path 'users/'+userId
    When method get
    Then status 200
	Then match response contains user_request
	
  Scenario: 03 Get all Users with verification of JSON
    Given path 'users'
    When method get
    Then status 200
    Then match response == '#array'
    Then match each response[*] ==
    """
        {
        "firstName": "#string",
        "lastName": "#string",
        "userId": "#uuid"
        }
    """	