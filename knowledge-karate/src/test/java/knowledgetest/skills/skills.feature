Feature: Verify Skills

  Background:
    Given url 'http://localhost:8081/'

  Scenario: 01 Get all Skills
    Given path 'skills'
    When method get
    Then status 200
    Then match response == '#array'
	
  Scenario: 02 Create a New Skill
    Given path 'skills'
	* def skill_request = { "skillDescription": "karate"}
    And request skill_request
    When method POST
    Then status 201
    * def skillId = response.skillId

    Given path 'skills/'+skillId
    When method get
    Then status 200
	Then match response contains skill_request
	
  Scenario: 03 Get all Skills with verification of JSON
    Given path 'skills'
    When method get
    Then status 200
    Then match response == '#array'
    Then match each response[*] ==
    """
        {
        "skillDescription": "#string",
        "skillId": "#uuid"
        }
    """	