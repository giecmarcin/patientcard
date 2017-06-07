angular.module('app').controller('PressureModalController', function ($scope, close, $element) {

    $scope.open4 = function() {
        $scope.popup4.opened = true;
    };
    $scope.popup4 = {
        opened: false
    };

    $scope.ok = function() {
        $element.modal('hide');
        close($scope.obj, 500);
    };

});