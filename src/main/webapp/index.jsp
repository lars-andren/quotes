<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html ng-app="angularapp">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="lib/angular.js"></script>
        <script src="lib/angular-resource.js"></script>
        <script src="js/controller.js"></script>

        <title>Quotes - Small AngularJS App</title>

        <style>
            body {
                font-family: verdana, sans-serif;
            }
        </style>
    </head>
    <body ng-controller="QuoteController">
        <fieldset>
            <legend><strong>Quote - RESTful Service</strong></legend>
            <h2>Add a new quote, or replace an existing one.</h2>
            <form ng-submit="newQuote()">
                <p><label>Name:</label><input type="text" ng-model="name"></p>
                <p><label>Quote:</label><input type="text" ng-model="quote"></p>
            </form>
            <h3>{{name + said}} "{{quote}}"</h3>

            <p><input type="button" ng-click="restPost()" value="Add/Replace"/></p>

            <h2>Quotes:</h2>
            <ul ng-repeat="quote in quotes">
                <li>{{quote.name + said}} "{{quote.quote}}"</li>
            </ul>

            <input type="button" ng-click="restGet()" value="See all quotes"/>

        </fieldset>
        
    </body>
</html>
