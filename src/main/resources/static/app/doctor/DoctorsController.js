angular.module('app').controller('DoctorsController', function ($rootScope, $scope, $filter, DoctorService, $location,
dialogs, $routeParams) {
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
    var  loadDoctorData = function(id){
            DoctorService
                .findOne(id)
                .then(function (response) {
                  $scope.newDoctor = response.data;
                  $scope.newDoctor.dayOfBirth = new Date($scope.newDoctor.dayOfBirth);
                }, function(response){
                  //error callback
                  dialogs.error('Error', 'Problem with download data.');
                })
    }
    $scope.disabledFields = false;
    if($routeParams.id!=undefined){
        loadDoctorData($routeParams.id);
         $scope.disabledFields = true;
    }else{
        $scope.disabledFields = false;
    }

    $scope.addDoctor = function () {
        // var date = {
        //     "dayOfBirth":$scope.dateOfBirth
        // }
        //var result = Object.assign({},$scope.newDoctor, date);
        $scope.newDoctor.fullName = $scope.newDoctor.firstName + ' ' + $scope.newDoctor.lastName;
        DoctorService
            .add($scope.newDoctor)
            .then(function (response) {
                if (response.status == 200) {
                    //$location.path("/doctors");
                    clearDoctorFields();
                    dialogs.notify("Information", "Doctor has been added");
                }else{
                    var errorMsg = '';
                    var errorMessage = '';
                    if(response.data.errors!=undefined){
                        errorMsg = response.data.errors.toString();
                    }
                    if(response.data.errorMessage!=undefined){
                        errorMessage = response.data.errorMessage;
                    }
                    dialogs.error("Error", "Problem with adding doctor. "
                    + "\Problems "+ errorMessage
                    + ":\n " + errorMsg
                    + " status: " + response.status);
                }
            })
    }

    function clearDoctorFields(){
        $scope.newDoctor = {};
        $scope.disabledFields = false;
    }
});