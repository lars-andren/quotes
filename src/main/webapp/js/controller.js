
// Defining the Angular Module and injecting the ngResource
var angularModule = angular.module("angularapp", ["ngResource"]);


// Service to fetch EmployeeListService
angularModule.factory("EmployeeListService", ['$resource', function($resource){
      return $resource('rest/employee/:empId',
                {empId: '@id'},
                {contactNumbers: {method: 'GET', params: {admin: true}, isArray: false}});  
}]);


// Service to fetch QuoteListService
angularModule.factory("QuoteListService", ['$resource', function($resource) {
    return $resource('rest/quote/all',
        {method: 'GET',
            params: {admin: true},
            isArray: true
        }
    )
}]);

// Service to fetch Reportees
angularModule.factory("EmployeeReporteeService", ['$resource', function($resource){
      return $resource('rest/employee/:empId/reportees',
                {empId: '@id'},
                /**
                 * Custom method to find contact numbers.
                 * 
                 * Method type is GET
                 * Additional Query parameter admin=true is sent
                 * The expected return type is an Array
                 */
                {contactNumbers: {method: 'GET', params: {admin: true}, isArray: true}});  
}]);


/**
 * 
 * Creating controller and injecting $http, EmployeeListService, EmployeeReporteeService
 * 
 * @param {type} param1
 * @param {type} param2
 */
angularModule.controller("EmployeeController", function($scope, $http, QuoteListService, EmployeeListService, EmployeeReporteeService) {

    /*
    // Default employe Object
    $scope.employee = {name: "Martin"};
    
    // Initial reportees which will be filled by RESTful call.*/
    $scope.reportees = [];

    $scope.quotes = [];
    $scope.said = " once said ";
/*
    // Call to servlet to fetch the employee details
    $scope.fetchGet = function() {
        $http.get("empMgr", 
                {
                    params: {op: 'get'},
                    // Passing the headers to the server for some prcessing
                    headers: {"AUTH_TOKEN": "1234567890"} 
                })
           .success(function(data, status, headers, config) {
                // On success updating the Employee Object.
                $scope.employee = data;
            }) 
          .error(function(data, status, headers, config) {
              alert("failure");
            });
        };

    // Sample method to post data to server.
    $scope.postData = function() {
        // Posting the firstName and lastName to the server.
        var dataToPost = {firstName: "Allen", lastName: "John"}; /* PostData*/
        
        // Configuring the query parameters.
    //    var queryParams = {params: {op: 'saveEmployee'}};/* Query Parameters*/

        // Posting the data to the Servlet.
 //       $http.post("empMgr" /* URL */, dataToPost, queryParams)
   //         .success(function(serverResponse, status, headers, config) {
                // Updating the $scope postresponse variable to update theview
     //           $scope.postresponse = serverResponse.data.firstName + " " + serverResponse.data.lastName;
       //     }).error(function(serverResponse, status, headers, config) {
         //       alert("failure");
           // }
        //);
//    };

    // Calling the RESTful service to fethch the employee details. 
    $scope.restGet = function() {
        QuoteListService.query(function(data) {
            for(var i=0; i<data.length; i++) {
                $scope.quotes[i] = data[i];
            }
        });
        //$scope.employeeDetails = EmployeeListService.get({empId: 10})
    };
    
    // Calling the RESTful service to query the details of Employee id 10
    $scope.restQuery = function() {
        EmployeeReporteeService.query({empId: 10}, function(data) {
            for(var i=0; i<data.length; i++) {
                $scope.reportees[i] = data[i];
            }
       });
    };
    
    $scope.contactNumbers = function() {
        $scope.contactNumbers = EmployeeReporteeService.contactNumbers({empId: 100});
    }
});

