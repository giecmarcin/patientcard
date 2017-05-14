var app = angular.module('app', ['ngRoute', 'ngResource', 'ui.bootstrap', 'ngStorage']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: '/app/views/user/login.html',
            controller: 'LoginController'
        })
        .when('/doctors', {
            templateUrl: '/app/views/doctor/doctors.html',
            controller: 'DoctorsController'
        })
        .when('/doctors/add', {
            templateUrl: '/app/views/doctor/add.html',
            controller: 'DoctorsController'
        })
        .otherwise({
            redirectTo: '/'
        });
});

app.run(function ($localStorage) {
    if ($localStorage.currentUser == undefined) {
        $localStorage.currentUser=null;
    }
});