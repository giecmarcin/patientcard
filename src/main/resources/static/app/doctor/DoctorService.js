angular.module('app').service('DoctorService', function($http) {

    this.add = function (doctor) {
        return $http({
            method: "POST",
            url: '/api/doctors/add',
            data: doctor,
            headers: {'Content-Type': 'application/json'}
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response;
        });
    }

    this.findAll = function () {
        var url = 'api/doctors/all';
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