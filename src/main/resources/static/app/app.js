var app = angular.module('app', ['ngRoute',
    'ngResource',
    'ui.bootstrap',
    'ngStorage',
    'chart.js']);

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
        .when('/nurses', {
            templateUrl: '/app/views/nurse/nurses.html',
            controller: 'NurseController'
        })
        .when('/nurses/add', {
            templateUrl: '/app/views/nurse/add.html',
            controller: 'NurseController'
        })
        .when('/patients', {
            templateUrl: '/app/views/patient/patients.html',
            controller: 'PatientsController'
        })
        .when('/patients/add', {
            templateUrl: '/app/views/patient/add.html',
            controller: 'PatientsController'
        })
        .when('/patients/details/:id', {
            templateUrl: '/app/views/patient/details.html',
            controller: 'DetailsController'
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