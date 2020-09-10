"use strict";

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

// first controller
module.controller('ProductController', function (productAPI, categoryAPI, productsAPI) {
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
    
     
    
   
});
module.controller('CustomerController', function (registerAPI, $window, signInAPI, $sessionStorage) {
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
});

