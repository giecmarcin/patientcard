angular.module('app').controller('MedicineModalController', function ($scope, close, $element) {

    $scope.open3 = function() {
        $scope.popup3.opened = true;
    };
    $scope.popup3 = {
        opened: false
    };

    $scope.ok = function() {
        $element.modal('hide');
        close($scope.obj, 500);
    };

});