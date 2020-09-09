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

// first controller
module.controller('ProductController', function (productAPI) {
    //alert("in controller");
    this.products = productAPI.query();
})

