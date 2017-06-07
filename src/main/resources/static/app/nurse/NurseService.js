angular.module('app').service('NurseService', function($http) {

    this.add = function (nurse) {
        return $http({
            method: "POST",
            url: '/api/nurses/add',
            data: nurse,
            headers: {'Content-Type': 'application/json'}
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response;
        });
    }

    this.findAll = function () {
        var url = 'api/nurses/all';
        return $http({
            method: "GET",
            url: url
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response;
        });
    }
    this.findOne = function (id) {
        var url = '/api/nurses/id/' + id;
        return $http({
            method: "GET",
            url: url
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
           return response;
        });
    }
});