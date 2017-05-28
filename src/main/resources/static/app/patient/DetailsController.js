angular.module('app').controller('DetailsController', function($rootScope, $scope, $filter, PatientService,
$location, $routeParams, ModalService, dialogs, PatientService, $filter) {
    var  loadPatientData = function(id){
            PatientService
                .findOne(id)
                .then(function (response) {
                  $scope.patient = response.data;
                  var temperaturesData = prepareTemperatures($scope.patient);
                  $scope.makeTemperatureChart(temperaturesData.labels, temperaturesData.dataY);
                }, function(response){
                  //error callback
                  dialogs.error('Error', 'Problem with download data.');
                })
    }
    loadPatientData($routeParams.id);


    var chartToTable = function(chart, table) {
        table.length = 0;
        for (var i = 0; i < chart.labels.length; i++) {
            var obj = {
                "day": chart.labels[i],
                "temperature": chart.data[0][i]
            }
            table.push(obj);
            //console.log(angular.toJson(obj));
        }

    }

    $scope.temperatureTable = [];
    //chartToTable($scope.temperatureChart, $scope.temperatureTable);

    $scope.showDialogTemperature = function() {
        ModalService.showModal({
            templateUrl: '/app/views/modal/temperatureModal.html',
            controller: "TemperatureModalController"
        }).then(function(modal) {
            modal.element.modal();
            modal.closed.then(function(temperature) {
                if (temperature) {
                    var temperatureObj = {
                        "temperature": temperature.temp,
                        "zonedDateTime": temperature.time
                    }
                    $scope.patient.temperatures.push(temperatureObj);
                    save($scope.patient);
                } else {
                    dialogs.error('Error', 'Problem with temperature.');
                }
            });
        });
    };

    var save = function(patient){
            PatientService
                .add(patient)
                .then(function (response) {
                    if(response.status==200){
                        loadPatientData($routeParams.id);
                        dialogs.notify('Information', 'Data has been saved.');
                    }else{
                        dialogs.error('Problem', 'Problem with saving data');
                    }
                })
    }

    function prepareTemperatures(patient){
        var sortedTemperatures = $filter('orderBy')(patient.temperatures, 'zonedDateTime');
        var onlyTemperatures = new Array();
        var labels = new Array();

        for(var i=0; i<sortedTemperatures.length; i++){
            onlyTemperatures.push(sortedTemperatures[i].temperature);
            var time = $filter('date')(sortedTemperatures[i].zonedDateTime, "dd.MM.yyyy hh:mm");
            labels.push(time);
        }
        var obj = {
            "labels": labels,
            "dataY" : onlyTemperatures
        }
        return obj;
    }

       $scope.makeTemperatureChart = function (dataX, dataY) { //metoda rysująca wykres
            $scope.labels = dataX;
            $scope.series = ['Temperature'];

            $scope.data = [
                dataY
            ];
            $scope.datasetOverride = [{fill: false}];
            $scope.options = {
                scales: {
                    yAxes: [
                        {
                            id: 'y-axis-1',
                            type: 'linear',
                            display: true,
                            position: 'left',
                        }
                    ]
                },
                elements: {
                    point: {
                        radius: 3
                    }
                }
            };
    }
});