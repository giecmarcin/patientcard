angular.module('app').controller('NurseController', function ($rootScope, $scope, $filter, NurseService, $location,
dialogs) {
    //$scope.dateOfBirth = $filter("date")(Date.now(), 'yyyy-MM-dd');
    $scope.dateOfBirth = new Date();
    $scope.newNurse = {};
    $scope.allNurses;

    $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    $scope.format = $scope.formats[2];
    $scope.open1 = function() {
        $scope.popup1.opened = true;
    };
    $scope.popup1 = {
        opened: false
    };

    var loadAllNurses = function () {
        NurseService
            .findAll()
            .then(function (response) {
                if (response.status == 200) {
                    $scope.allNurses = response.data;
                }
            })
    }
    loadAllNurses();
    $scope.addNurse = function () {
        NurseService
            .add($scope.newNurse)
            .then(function (response) {
                if (response.status == 200) {
                    clearNurseFields();
                    //$location.path("/nurses");
                    dialogs.notify("Information", "Nurse has been added");
                }else{
                    var errorMsg = '';
                    var errorMessage = '';
                    if(response.data.errors!=undefined){
                        errorMsg = response.data.errors.toString();
                    }
                    if(response.data.errorMessage!=undefined){
                        errorMessage = response.data.errorMessage;
                    }
                    dialogs.error("Error", "Problem with adding nurse. "
                    + "\Problems "+ errorMessage
                    + ":\n " + errorMsg
                    + " status: " + response.status);
                }
            })
    }

    function clearNurseFields(){
        $scope.newNurse = {};
    }
});