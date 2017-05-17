angular.module('app').controller('DetailsController', function ($rootScope, $scope, $filter, PatientService, $location, $routeParams) {

    $scope.temperatureChart = {}
    $scope.temperatureChart.labels = ["2017-05-01", "2017-05-02", "2017-05-03", "2017-05-04", "2017-05-04", "2017-05-05", "2017-05-06"];
    $scope.temperatureChart.series = ['Series A'];
    $scope.temperatureChart.data = [
        [35, 36, 37, 38, 38, 36.6, 36.6]
    ];
    $scope.temperatureChart.datasetOverride = [{fill: false}, {fill: false}];
    $scope.temperatureChart.options = {
        responsive: false,
        maintainAspectRatio: false,
        scales: {
            yAxes: [
                {
                    id: 'y-axis-1',
                    type: 'linear',
                    display: true,
                    position: 'left'
                }
            ]
        }
    };
});