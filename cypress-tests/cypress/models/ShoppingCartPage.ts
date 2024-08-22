import BUYING_PRODUCT_SELECTORS from "@integration/buyingProduct/buying_product_selectors";


export class ShoppingCartPage{

    get goToShoppingCart(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.shoppingCart.goToShoppingCart
        );
    }

    get removeProduct(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.shoppingCart.removeProduct
        );
    }

    get goToCheckout(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.shoppingCart.goToCheckout
        );
    }
}