"use strict";

class SaleItem {

    constructor(product, quantity) {
        // only set the fields if we have a valid product
        if (product) {
            this.product = product;
            this.quantityPurchased = quantity;
            this.salePrice = product.listPrice;
        }
    }

    getItemTotal() {
        return this.salePrice * this.quantityPurchased;
    }

}

class ShoppingCart {

    constructor() {
        this.items = new Array();
    }

    reconstruct(sessionData) {
        for (let item of sessionData.items) {
            this.addItem(Object.assign(new SaleItem(), item));
        }
    }

    getItems() {
        return this.items;
    }

    addItem(item) {
        this.items.push(item);
    }

    setCustomer(customer) {
        this.customer = customer;
    }

    getTotal() {
        let total = 0;
        for (let item of this.items) {
            total += item.getItemTotal();
        }
        return total;
    }

}

// create a new module, and load the other pluggable modules
var module = angular.module('ShoppingApp', ['ngResource', 'ngStorage']);

// get the products from the web service
module.factory('productAPI', function ($resource) {
    return $resource('/api/products/:id');
});
module.factory('categoryAPI', function ($resource) {
    return $resource('/api/categories/:cat');
});
module.factory('registerAPI', function ($resource) {
    return $resource('/api/register');
});
module.factory('signInAPI', function ($resource) {
   return $resource('/api/customers/:username');
});
module.factory('productsAPI', function ($resource) {
    return $resource('/api/products/');
});
module.factory('salesAPI', function ($resource) {
    return $resource('/api/sales/');
});

module.factory('cart', function ($sessionStorage) {
    let cart = new ShoppingCart();

    // is the cart in the session storage?
    if ($sessionStorage.cart) {

        // reconstruct the cart from the session data
        cart.reconstruct($sessionStorage.cart);
    }

    return cart;
});

// first controller
module.controller('ProductController', function (productAPI, categoryAPI, productsAPI, $window) {
    //alert("in controller");
    this.products = productAPI.query();
    this.categories = categoryAPI.query();

    // click handler for the category filter buttons
    this.selectCategory = function (selectedCat) {
        this.products = categoryAPI.query({"cat": selectedCat});
    };
    
    this.allProducts = function () {
        this.products = productsAPI.query();
    };
    
    this.viewProducts = function () {
        $window.location = 'products.html';
    };
    
    this.viewHome = function () {
        $window.location = '.';
    };
    
    this.viewSignin = function () {
        $window.location = 'signin.html';
    }; 
    
    this.viewCart = function () {
        $window.location = 'cart.html';
    };
});
module.controller('CustomerController', function (registerAPI, $window, signInAPI, $sessionStorage) {
    this.customer1 = $sessionStorage.customer;
    this.registerCustomer = function (customer) {
        registerAPI.save(null, customer,
            // success callback
            function () {
                $window.location = 'signin.html';
            },
            // error callback
            function (error) {
                console.log(error);
            }
        );
    };
    this.signInMessage = "Please sign in to continue.";
    
            // alias 'this' so that we can access it inside callback functions
            let ctrl = this;

            this.signIn = function (username, password) {

                // get customer from web service
                signInAPI.get({'username': username},
                        // success callback
                                function (customer) {
                                    // also store the retrieved customer
                                    $sessionStorage.customer = customer;

                                    // redirect to home
                                    $window.location = '.';
                                },
                                // fail callback
                                        function () {
                                            ctrl.signInMessage = 'Sign in failed. Please try again.';
                                        }
                                );
                            };
                            
    this.signOut = function () {
        delete $sessionStorage.customer;
        $window.location = '.';
    };      
    this.btnSignIn = function () {
        $window.location = 'signin.html';
    };
});

module.controller('ShoppingCartController', function (cart, $sessionStorage, $window, salesAPI) {
    this.items = cart.getItems();
    var num = cart.getTotal();
    this.total = num.toFixed(2);
    this.selectedProduct = $sessionStorage.product;
    
    this.buyProduct = function (product) {
        $sessionStorage.product = product;
        $window.location = 'quantity.html';
    };
    
    this.addToCart = function () {
       let quantity = document.getElementById('quantity').value;
       let sessionProduct = $sessionStorage.product;
       let item = new SaleItem(sessionProduct, quantity);
       cart.addItem(item);
       $sessionStorage.cart = cart;
       delete $sessionStorage.product;
       $window.location = 'products.html';
    };
    
    this.checkout = function () {
       let sessionCustomer = $sessionStorage.customer;
       cart.setCustomer(sessionCustomer);
       salesAPI.save(cart);
       delete $sessionStorage.cart;
       $window.location = 'thankyou.html';
    };
});
