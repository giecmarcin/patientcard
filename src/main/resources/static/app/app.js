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
    'angularModalService',
    'ui.bootstrap.datetimepicker',
    'datetimepicker'
    ]);

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
        setFields();
    });

    $rootScope.showManagementLink = false;
    $rootScope.showDoctorsLink = false;
    $rootScope.showPatientsLink = false;
    $rootScope.showNursesLink = false;
    $rootScope.disableInPatientDetails = true;
    $rootScope.showInPatientDetails = false;

    $rootScope.$on('$routeChangeSuccess', function (e, current, pre) {
         if($sessionStorage.currentUser!=undefined){
             setFields();
             var currentPath = current.$$route.originalPath;
             var role = $sessionStorage.currentUser.role;
             if(angular.equals(role, "PATIENT")){
                $location.path('patients/details/' + $sessionStorage.currentUser.id);
             }
             else if(angular.equals(role,"ADMIN")){
                //Do nothing
             }else if(angular.equals(role,"DOCTOR")){
                 if(angular.equals(currentPath,"/doctors")
                 ||(angular.equals(currentPath,"/doctors/add")
                 ||(angular.equals(currentPath,"/nurses"))
                 ||(angular.equals(currentPath,"/nurses/add")))
                 ){
                     $location.path('/welcome');
                 }
             }else if(angular.equals(role,"NURSE")){
                 if(angular.equals(currentPath,"/doctors")
                 ||(angular.equals(currentPath,"/doctors/add")
                 ||(angular.equals(currentPath,"/nurses"))
                 ||(angular.equals(currentPath,"/nurses/add")))
                 ){
                     $location.path('/welcome');
                 }
             }
         }
    });

    function setFields(){
         if($sessionStorage.currentUser!=undefined){
            var role = $sessionStorage.currentUser.role;
            if(angular.equals(role,"ADMIN")){
                $rootScope.showManagementLink = true;
                $rootScope.showDoctorsLink = true;
                $rootScope.showPatientsLink = true;
                $rootScope.showNursesLink = true;
                $rootScope.disableInPatientDetails = false;
                $rootScope.showInPatientDetails = true;
            }else if(angular.equals(role,"DOCTOR")){
                $rootScope.showManagementLink = true;
                $rootScope.showDoctorsLink = false;
                $rootScope.showPatientsLink = true;
                $rootScope.showNursesLink = false;
                $rootScope.disableInPatientDetails = false;
                $rootScope.showInPatientDetails = true;
            }else if(angular.equals(role,"NURSE")){
                $rootScope.showManagementLink = true;
                $rootScope.showDoctorsLink = false;
                $rootScope.showPatientsLink = true;
                $rootScope.showNursesLink = false;
                $rootScope.disableInPatientDetails = false;
                $rootScope.showInPatientDetails = true;
            }else if(angular.equals(role,"PATIENT")){
                $rootScope.showManagementLink = false;
                $rootScope.showDoctorsLink = false;
                $rootScope.showPatientsLink = false;
                $rootScope.showNursesLink = false;
                $rootScope.disableInPatientDetails = true;
                $rootScope.showInPatientDetails = false;

            }
         }
    }

app.directive('formattedDate', function(dateFilter) {
  return {
    require: 'ngModel',
    scope: {
      format: "="
    },
    link: function(scope, element, attrs, ngModelController) {
      ngModelController.$parsers.push(function(data) {
        //convert data from view format to model format
        return dateFilter(data, scope.format); //converted
      });

      ngModelController.$formatters.push(function(data) {
        //convert data from model format to view format
        return dateFilter(data, scope.format); //converted
      });
    }
  }
});
});