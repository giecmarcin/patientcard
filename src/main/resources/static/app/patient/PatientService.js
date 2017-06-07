angular.module('app').service('PatientService', function($http) {

    this.add = function (patient) {
        return $http({
            method: "POST",
            url: '/api/patients/add',
            data: patient,
            headers: {'Content-Type': 'application/json'}
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response;
        });
    }

    this.findAll = function () {
        var url = '/api/patients/all';
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
        var url = '/api/patients/id/' + id;
        return $http({
            method: "GET",
            url: url
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
           return response;
        });
    }
    this.makeReport = function (id) {
        var url = '/api/reports/patients/id/' + id;
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