import BUYING_PRODUCT_SELECTORS from "@integration/buyingProduct/buying_product_selectors";


export class CheckoutPage{

    get emailInput(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.checkout.emailInput
        );
    }

    get firstNameInput(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.checkout.firstNameInput
        );
    }

    get lastNameInput(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.checkout.lastNameInput
        );
    }

    get countryInput(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.checkout.countryInput
        );
    }

    get cityInput(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.checkout.cityInput
        );
    }

    get postcodeInput(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.checkout.postcodeInput
        );
    }

    get addressInput(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.checkout.addressInput
        );
    }

    get selectPaymentMethod(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.checkout.selectPaymentMethod
        );
    }

    get checkoutButton(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.checkout.checkoutButton
        );
    }
}