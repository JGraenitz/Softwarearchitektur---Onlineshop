import BUYING_PRODUCT_SELECTORS from "@integration/buyingProduct/buying_product_selectors";


export class ViewProductPage{

    get chooseQuantity(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.viewProduct.chooseQuantity
        );
    }

    get addToCart(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.viewProduct.addToCart
        );
    }
}