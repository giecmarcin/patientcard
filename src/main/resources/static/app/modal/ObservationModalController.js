angular.module('app').controller('ObservationModalController', function ($scope, close, $element) {

    $scope.open2 = function() {
        $scope.popup2.opened = true;
    };
    $scope.popup2 = {
        opened: false
    };

    $scope.ok = function() {
        $element.modal('hide');
        close($scope.obj, 500);
    };

});