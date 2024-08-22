
const BUYING_PRODUCT_SELECTORS = {
    viewProduct: {
        catalogOverview: '#productPageNavButton',
        chooseProduct: '[class="product-link"]',
        chooseQuantity: '#product-details-quantity',
        addToCart: '#product-details-add-to-cart'
    },
    shoppingCart: {
        goToShoppingCart: '#basketHeaderButton',
        removeProduct: '',
        goToCheckout: '#checkoutBasketButton'
    },
    checkout: {
        emailInput: '#email',
        firstNameInput: '#vorname',
        lastNameInput: '#nachname',
        countryInput: '#country',
        cityInput: '#stadt',
        postcodeInput: '#postleitzahl',
        addressInput: '#adresse',
        selectPaymentMethod: '#paymentMethod',
        checkoutButton: '#payNowCheckoutButton'
    }
};
export default BUYING_PRODUCT_SELECTORS;