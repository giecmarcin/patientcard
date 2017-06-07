angular.module('app').controller('DetailsController', function($rootScope, $scope, $filter, PatientService,
$location, $routeParams, ModalService, dialogs, PatientService, $filter, DoctorService) {
    $scope.open1DetailPatient = function() {
        $scope.popup1DetailPatient.opened = true;
    };
    $scope.popup1DetailPatient = {
        opened: false
    };

    $scope.open2DetailPatient = function() {
        $scope.popup2DetailPatient.opened = true;
    };
    $scope.popup2DetailPatient = {
        opened: false
    };

    $scope.ok = function() {
        $element.modal('hide');
        close($scope.temperature, 500);
    };
    var  loadPatientData = function(id){
            PatientService
                .findOne(id)
                .then(function (response) {
                  $scope.patient = response.data;
                  $scope.patient.dayOfBirth = new Date($scope.patient.dayOfBirth);
                  $scope.patient.dateOfDeparture = new Date($scope.patient.dateOfDeparture);
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

    $scope.addObservation = function(){
            ModalService.showModal({
                templateUrl: '/app/views/modal/ObservationModal.html',
                controller: "ObservationModalController"
            }).then(function(modal) {
                modal.element.modal();
                modal.closed.then(function(obj) {
                    if (obj) {
                        var observationObj = {
                            "description": obj.description,
                            "zonedDateTime": obj.time
                        }
                        $scope.patient.observations.push(observationObj);
                        save($scope.patient);
                    } else {
                        dialogs.error('Error', 'Problem with observation.');
                    }
                });
            });
    }

    $scope.addMedicine = function(){
            ModalService.showModal({
                templateUrl: '/app/views/modal/MedicineModal.html',
                controller: "MedicineModalController"
            }).then(function(modal) {
                modal.element.modal();
                modal.closed.then(function(obj) {
                    if (obj) {
                        var medicineObj = {
                            "name": obj.name,
                            "quantity": obj.quantity,
                            "zonedDateTime": obj.time
                        }
                        $scope.patient.medicines.push(medicineObj);
                        save($scope.patient);
                    } else {
                        dialogs.error('Error', 'Problem with adding medicine.');
                    }
                });
            });
    }

    $scope.updatePatient = function(patient){
        //prepareFullNamesForDoctors(patient);
        save(patient);
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

    function prepareFullNamesForDoctors(patient){
        if(patient.doctors!=undefined){
            for(var i=0; i<patient.doctors.length;i++){
                patient.doctors[i].fullName = patient.doctors[i].firstName + ' ' + patient.doctors[i].lastName;
            }
        }
    }
    $scope.addPressure = function(){
            ModalService.showModal({
                templateUrl: '/app/views/modal/PressureModal.html',
                controller: "PressureModalController"
            }).then(function(modal) {
                modal.element.modal();
                modal.closed.then(function(obj) {
                    if (obj) {
                        var pressureObj = {
                            "pressureSystolic": obj.pressureSystolic,
                            "pressureDiastolic": obj.pressureDiastolic,
                            "zonedDateTime": obj.time
                        }
                        $scope.patient.pressures.push(pressureObj);
                        save($scope.patient);
                    } else {
                        dialogs.error('Error', 'Problem with adding medicine.');
                    }
                });
            });
    }

    $scope.makeReport = function(){
            PatientService
                .makeReport($routeParams.id)
                .then(function (response) {
                    if(response.status==200){
                        loadPatientData($routeParams.id);
                        dialogs.notify('Information', 'Report has been generated.');
                    }else{
                        dialogs.error('Problem', 'Problem with generate report.');
                    }
                })
    }

});