import {When, Then, DataTable } from '@badeball/cypress-cucumber-preprocessor';
import {ProductOverviewPage} from "@models/ProductOverviewPage";
import {ShoppingCartPage} from "@models/ShoppingCartPage";
import {ViewProductPage} from "@models/ViewProductPage";
import {CheckoutPage} from "@models/CheckoutPage";
import BUYING_PRODUCT_SELECTORS from "@integration/buyingProduct/buying_product_selectors";

const productOverviewPage: ProductOverviewPage = new ProductOverviewPage();
const viewProductPage: ViewProductPage = new ViewProductPage();
const shoppingCartPage: ShoppingCartPage = new ShoppingCartPage();
const checkoutPage: CheckoutPage = new CheckoutPage();

const homepage = "localhost:3000";

When("I visit {string}", (url: string) => {
    cy.visit(url)
})

When("I login in with username {string} and password {string}", (username: string, password: string) => {

})

Then("I should be redirected to {string}", (url: string) => {
    cy.url().then((currentUrl) => {
        expect(currentUrl).to.include(url);
    })
})

When("I go to view the product catalog", () => {
    productOverviewPage.catalogOverview.click();
})

When("I click on the first product", () => {
    productOverviewPage.chooseProduct.first().click();
})

When("I choose a quantity of {int}", (quantity: number) => {
    viewProductPage.chooseQuantity.clear().type(quantity.toString());
})

When("I add the product(s) to the shopping cart", () => {
    viewProductPage.addToCart.click();
})

When("I go to the shopping cart", () => {
    shoppingCartPage.goToShoppingCart.click();
})

When("I go to checkout", () => {
    shoppingCartPage.goToCheckout.click();
})

When("I enter the following values into the form:", (infoToEnter: DataTable) => {

    const info = infoToEnter.hashes();

    info.forEach((row, index) => {
        const email = row['Email'];
        checkoutPage.emailInput.clear().type(email);
        const firstName = row['First Name'];
        checkoutPage.firstNameInput.clear().type(firstName);
        const lastName = row['Last Name'];
        checkoutPage.lastNameInput.clear().type(lastName);
        const country = row['Country'];
        checkoutPage.countryInput.click().type(country);
        const city = row['City'];
        checkoutPage.cityInput.click().type(city);
        const postcode = row['Postcode'];
        checkoutPage.postcodeInput.click().type(postcode);
        const address = row['Address'];
        checkoutPage.addressInput.click().type(address);
    })
})

When("I choose {string} as a payment method", (payment: string) => {
    checkoutPage.selectPaymentMethod.select(payment);
})

When("I click on the checkout button", () => {
    checkoutPage.checkoutButton.click();
})