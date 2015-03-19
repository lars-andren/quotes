
// Defining the Angular Module and injecting the ngResource
var angularModule = angular.module("angularapp", ["ngResource"]);

// Service to fetch QuoteListService
angularModule.factory("QuoteListService", ['$resource', function($resource) {
    return $resource('rest/quote', {}, {
        query: {method: 'GET', isArray: true}
    })
}]);


angularModule.factory("QuotePostService", ['$resource', function($resource) {
    return $resource('rest/quote/:name/:quote', {}, {
        create: {method: 'POST', params: {name: '@name', quote: '@quote'}}
    })
}]);


/**
 * 
 * Creating controller and injecting $http, EmployeeListService, EmployeeReporteeService
 * 
 * @param {type} param1
 * @param {type} param2
 */
angularModule.controller("QuoteController", function($scope, $http, QuoteListService, QuotePostService) {

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
});

