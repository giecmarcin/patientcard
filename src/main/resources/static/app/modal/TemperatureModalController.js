angular.module('app').controller('TemperatureModalController', function ($scope, close, $element) {

    $scope.open1 = function() {
        $scope.popup1.opened = true;
    };
    $scope.popup1 = {
        opened: false
    };

    $scope.ok = function() {
        $element.modal('hide');
        close($scope.temperature, 500);
    };
    
});