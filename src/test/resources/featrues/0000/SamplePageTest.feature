#Author: Kavinda
#Keywords
#Page Navigation
Feature: Navigate through the application
  Scenario: 1 Navigate to Sample page module
    When User Hover the Tester's hub dropdown
    Then Users Clicks the SamplePageButton to Navigate page
    And Verify page title is "Sample Page Test - GlobalSQA"


  Scenario: 2 Data entering
    When User enter following data on add Sample Test page
      | Field Name    | Controller    | Input Type | Value                    | Controller Type |
      | Name          | g2599-name    | Text       | Kavinda                  | id              |
      | Email         | g2599-email   | Text       | test@test.com            | id              |
      | URL           | g2599-website | Text       | https://www.google.com/  | id              |




#
#      | Email         | email      | Text       | test@test.test          | formController  |
#
#      | Title         | title      | Dropdown   | Mr                      | formController  |
#      | Full Name     | fullName   | Text       | Tharushi Gimhara        | formController  |
#      | User Type     | userType   | Dropdown   | Enterprise              | formController  |
#      | Language      | language   | Dropdown   | English - United States | formController  |
#      | Time Zone     | timeZone   | Dropdown   | GMT +04:00 Oman Time    | formController  |
#      | Company Name  | company    | Dropdown   | Company XYZ_ELVs        | formController  |
#      | Gender        | gender     | Dropdown   | Female                  | formController  |
#      | Mobile No     | mobileNo   | Text       | +9475378636             | formController  |
#      | Date Of Birth | Join Date  | Calendar   | Random Date             | formController  |

