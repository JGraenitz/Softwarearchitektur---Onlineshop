import {When, Then, DataTable } from '@badeball/cypress-cucumber-preprocessor';
import {ProductOverviewPage} from "@models/ProductOverviewPage";
import {ShoppingCartPage} from "@models/ShoppingCartPage";
import {ViewProductPage} from "@models/ViewProductPage";
import {CheckoutPage} from "@models/CheckoutPage";
import {LoginPage} from "@models/LoginPage";

const loginPage: LoginPage = new LoginPage();

When("I enter {string} and {string} and login", (username: string, password: string) => {
    loginPage.usernameInput.clear().type(username);
    loginPage.passwordInput.clear().type(password);
    loginPage.loginButton.click();
})
