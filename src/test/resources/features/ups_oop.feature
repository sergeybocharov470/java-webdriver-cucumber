Feature: ups_oop


@ups3
  Scenario: UPS end to end first OOP
  #Day14  implemented 20200923
  Given I go to "ups" page oop
  And I open Shipping menu oop
  And I go to Create a Shipment oop
  When I fill out origin shipment fields oop
  And I submit the shipment form oop
  Then I verify origin shipment fields submitted oop
  And I cancel the shipment form oop
  Then I verify shipment form is reset oop