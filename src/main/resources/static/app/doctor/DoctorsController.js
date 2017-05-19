angular.module('app').controller('DoctorsController', function ($rootScope, $scope, $filter, DoctorService, $location) {
    //$scope.dateOfBirth = $filter("date")(Date.now(), 'yyyy-MM-dd');
    $scope.dateOfBirth;
    $scope.newDoctor = {};
    $scope.allDoctors;

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[2];
    $scope.open1 = function() {
        $scope.popup1.opened = true;
    };
    $scope.popup1 = {
        opened: false
    };

    var loadAllDoctors = function () {
        DoctorService
            .findAll()
            .then(function (response) {
                if (response.status == 200) {
                   $scope.allDoctors = response.data;
                }
            })
    }
    loadAllDoctors();
    $scope.addDoctor = function () {
        // var date = {
        //     "dayOfBirth":$scope.dateOfBirth
        // }
        //var result = Object.assign({},$scope.newDoctor, date);
        DoctorService
            .add($scope.newDoctor)
            .then(function (response) {
                if (response.status == 200) {
                    $location.path("/doctors");
                }
            })
    }
});