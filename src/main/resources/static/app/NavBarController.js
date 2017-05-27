angular.module('app').controller('NavBarController', function($scope, $localStorage, $rootScope,
LoginService, $location, $sessionStorage) {

    $rootScope.email='';
    var isLoggedUser = function() {
        if ($sessionStorage.currentUser != undefined) {
            $rootScope.showNavBar = $sessionStorage.showNavBar;
            $rootScope.email = $sessionStorage.currentUser.email;
        }
    }
    isLoggedUser();

    $scope.logoutUser = function () {
        LoginService
            .logoutUser()
            .then(function (response) {
                $sessionStorage.showNavBar = false;
                $rootScope.showNavBar = false;
                $sessionStorage.currentUser = null;
                $location.path('/login');

            });
    }

    $scope.changeLanguage = function(key) {
        $translate.use(key);
    };
});