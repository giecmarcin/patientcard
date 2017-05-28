var app = angular.module('app', ['ngRoute',
    'ngResource',
    'ui.bootstrap',
    'ngStorage',
    'chart.js',
    'pascalprecht.translate',
    'ngSanitize',
    'dialogs.main',
    'angular-loading-bar',
    'cfp.loadingBar',
    'multipleSelect',
    'angularModalService']);

app.config(function($routeProvider) {
    $routeProvider
        .when('/login', {
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
        .when('/welcome', {
            templateUrl: '/app/views/welcome.html',
            controller: 'NavBarController'
        })
        .otherwise({
            redirectTo: '/'
        });
});

app.config(['cfpLoadingBarProvider', function(cfpLoadingBarProvider) {
  cfpLoadingBarProvider.includeSpinner = false;
  cfpLoadingBarProvider.includeBar = true;
  cfpLoadingBarProvider.loadingBarTemplate = '<div id="loading-bar"><div class="bar"><div class="peg"></div></div></div>';
}]);

app.config(['$translateProvider', function($translateProvider) {
    $translateProvider.useSanitizeValueStrategy('escape');
    $translateProvider.translations('en', {
        'DIALOGS_YES': 'Yes',
        'DIALOGS_NO': 'No',
        'DIALOGS_OK': 'Ok',
        'DIALOGS_CLOSE': 'Close',
    });

    $translateProvider.translations('de', {
        'DIALOGS_YES': 'Ja',
        'DIALOGS_NO': 'Nein',
        'DIALOGS_OK': 'Ok',
        'DIALOGS_CLOSE': 'Schließen',
    });
    $translateProvider.preferredLanguage('en');
}]);

app.run(function($localStorage, $sessionStorage, $rootScope, LoginService, $location) {
    var checkUserInLocalStorage = function() {
        if ($sessionStorage.currentUser == undefined) {
            $sessionStorage.currentUser = null;
            $sessionStorage.showNavBar = false;
            $location.path('/login');
        } else {
            $sessionStorage.showNavBar = true;
        }
    };
    checkUserInLocalStorage();

    $rootScope.$on("$locationChangeStart", function(event, next, current) {
        checkUserInLocalStorage();
    });
});