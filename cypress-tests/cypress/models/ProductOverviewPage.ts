import BUYING_PRODUCT_SELECTORS from "@integration/buyingProduct/buying_product_selectors";


export class ProductOverviewPage{

    get catalogOverview(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.viewProduct.catalogOverview
        );
    }

    get chooseProduct(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            BUYING_PRODUCT_SELECTORS.viewProduct.chooseProduct
        );
    }
}