angular.module('app').service('LoginService', function($http) {
    this.login = function(userLoginAndPassword) {
        var data = 'username=' + userLoginAndPassword.username + '&password=' + userLoginAndPassword.password;
        return $http({
            method: "POST",
            url: 'authenticate',
            data: data,
            ignoreAuthModule: 'ignoreAuthModule',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response;
        });
    };
    this.getCurrentUser = function() {
        var url = 'security/account';
        return $http({
            method: "GET",
            url: url
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response;
        });
    };
    this.logoutUser = function() {
        var url = '/logout';
        return $http({
            method: "GET",
            url: url
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response;
        });
    };
});