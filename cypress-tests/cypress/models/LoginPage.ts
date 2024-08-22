import LOGIN_SELECTORS from "@integration/buyingProduct/login_selectors";

export class LoginPage{

    get usernameInput(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            LOGIN_SELECTORS.login.usernameInput
        );
    }

    get passwordInput(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            LOGIN_SELECTORS.login.passwordInput
        );
    }

    get loginButton(): Cypress.Chainable<JQuery<HTMLElement>> {
        return cy.get(
            LOGIN_SELECTORS.login.loginButton
        );
    }
}