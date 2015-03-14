<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html ng-app="angularapp">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="angular.js"></script>
        <script src="angular-resource.js"></script>
        <script src="controller.js"></script>
        <title>Angular Application</title>
        <style>
            body {
                font-family: verdana, sans-serif;
            }
        </style>
    </head>
    <body ng-controller="EmployeeController">
        <fieldset>
            <legend><strong>Employee Information</strong></legend>
            <h3>Fetch GET request : {{employee.firstName}} {{employee.lastName}}</h3><input type="button" ng-click="fetchGet();" value="Get"/>
            <h3>Post Response {{postresponse}} </h3> 
            <input type="button" ng-click="postData();" value="Post"/>
        </fieldset>  
        <fieldset>
            <legend><strong>RESTful Service</strong></legend>
            <h3>Fetch Employee Details: {{employeeDetails.id}} , {{employeeDetails.firstName}} {{employeeDetails.lastName}}</h3>
            <input type="button" ng-click="restGet()" value="Fetch Rest call"/>
            <h3>Fetch Reportee Details:</h3>
            <ul ng-repeat="reportee in reportees">
                <li>{{reportee.firstName}} {{reportee.lastName}}</li>
            </ul>
            <input type="button" ng-click="restQuery()" value="Fetch using REST Query"/>
            <h3>Invoking Custom Method</h3>
            <ul ng-repeat="contactNum in contactNumbers">
                <li>{{contactNum.number}}</li>
            </ul>
            <input type="button" ng-click="contactNumbers()" value="Fetch using REST Query"/>
        </fieldset>
        
    </body>
</html>
