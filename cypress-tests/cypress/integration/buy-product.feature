Feature: Buy products

  Background:
    When I visit "localhost:80"
    And I go to view the product catalog
    And I enter "sara.heine@gmx.de" and "AYPA" and login
    Then I should be redirected to "/productList"

  Scenario: Buy one product
    When I click on the first product
    Then I should be redirected to "/productdetails"
    When I choose a quantity of 2
    And I add the products to the shopping cart
    And I go to the shopping cart
    And I go to checkout
    Then I should be redirected to "/checkout"
    When I enter the following values into the form:
      | Email             | First Name | Last Name | Country     | City   | Postcode | Address        |
      | sara.heine@gmx.de | Sara       | Heine     | Deutschland | Berlin | 11111    | Wilhelminenhof |
    And I choose "PayPal" as a payment method
    And I click on the checkout button