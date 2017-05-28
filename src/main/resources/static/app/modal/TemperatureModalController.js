angular.module('app').controller('TemperatureModalController', function ($scope, close, $element) {
    $scope.ok = function() {
        $element.modal('hide');
        close($scope.temperature, 500);
    };
    
});