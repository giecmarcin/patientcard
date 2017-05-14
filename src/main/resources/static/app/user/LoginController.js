angular.module('app').controller('LoginController', function ($rootScope, $scope, LoginService, $location, $localStorage, $window, $timeout) {
    $scope.username = '';
    $scope.password = '';


    $scope.login = function () {
        var userLoginAndPassword = {
            "username": $scope.username,
            "password": $scope.password
        }
        LoginService
            .login(userLoginAndPassword)
            .then(function (response) {
                if (response.status == 200) {
                    LoginService
                        .getCurrentUser().then(function (response) {
                        $localStorage.currentUser = response.data;
                        if (angular.equals(response.data.role, 'ADMIN')) {
                            $timeout(reloadWithTimeout, 500);

                        } else {
                            $timeout(reloadWithTimeout, 500);

                        }
                        //$location.path('/');
                        alert('Logged');
                    })
                } else {
                    alert('Error');
                }
            })
    }

    function reloadWithTimeout() {
        $window.location.reload();
    }
});