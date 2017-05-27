angular.module('app').controller('PatientsController', function ($rootScope, $scope, $filter,
PatientService, $location, DoctorService, dialogs) {
    //$scope.dateOfBirth = $filter("date")(Date.now(), 'yyyy-MM-dd');
    $scope.dateOfBirth = new Date();
    $scope.dateOfDeparture = new Date();
    $scope.newPatient = {};
    $scope.allPatients;

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[2];
    $scope.open1 = function() {
        $scope.popup1.opened = true;
    };
    $scope.popup1 = {
        opened: false
    };

    $scope.open2 = function() {
        $scope.popup2.opened = true;
    };
    $scope.popup2 = {
        opened: false
    };

    var loadAllPatients = function () {
        PatientService
            .findAll()
            .then(function (response) {
                if (response.status == 200) {
                   $scope.allPatients = response.data;
                }
            })
    }
    loadAllPatients();
    $scope.addPatient = function () {
        // var dates = {
        //     "dayOfBirth":$scope.dateOfBirth,
        //     "dateOfDeparture": $scope.dateOfDeparture
        // }
        // var result = Object.assign({},$scope.newPatient, dates);
        PatientService
            .add($scope.newPatient)
            .then(function (response) {
                if (response.status == 200) {
                    $location.path("/patients");
                }
            })
    }
    $scope.allDoctors = new Array();
    var loadAllDoctors = function(doctors){
        $scope.allDoctors.length = 0;
        DoctorService
            .findAll()
                .then(function(response) {
                    //doctors = response.data;
                    $scope.allDoctors = response.data;
                },function(response){
                    dialogs.notify('Information', 'Problem with download data.');
                    return null;
                }
            );
    }
    loadAllDoctors();
});