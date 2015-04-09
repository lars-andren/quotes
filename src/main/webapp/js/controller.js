
// Defining the Angular Module and injecting the ngResource
var angularModule = angular.module("angularapp", ["ngResource"]);

// Service to do READ
angularModule.factory("QuoteListService", ['$resource', function($resource) {
    return $resource('rest/quote', {}, {
        query: {method: 'GET', isArray: true}
    })
}]);

// Service to do POST
angularModule.factory("QuotePostService", ['$resource', function($resource) {
    return $resource('rest/quote/:name/:quote', {}, {
        create: {method: 'POST', params: {name: '@name', quote: '@quote'}}
    })
}]);

// Service to do DELETE
angularModule.factory("QuoteDeleteService", ['$resource', function($resource) {
    return $resource('rest/quote/:name', {}, {
        delete: {method: 'DELETE', params: {name: '@name'}}
    })
}]);

/**
 */
angularModule.controller("QuoteController", function($scope, QuoteListService, QuotePostService, QuoteDeleteService) {

    $scope.quotes = [];
    $scope.said = " once said ";

    //TODO, add message after storing quote
    $scope.postMessage = "message";

    // Calling the RESTful service to fetch all the quotes from the database.
    $scope.restGet = function() {
        QuoteListService.query(function(data) {
            for(var i=0; i<data.length; i++) {
                $scope.quotes[i] = data[i];
             }
        });
    };

    // Calling the RESTful service to create a new quote in the database.
    $scope.restPost = function() {
        if ($scope.name != null && $scope.quote != null) {
            QuotePostService.create({name: $scope.name, quote: $scope.quote});
        }
    };

    // Calling the RESTful service to delete a quote in the database.
    $scope.restDelete = function() {
        $scope.deletehappened = "DONE DEL";
        QuoteDeleteService.delete({name: $scope.deleteName});
    };
});
